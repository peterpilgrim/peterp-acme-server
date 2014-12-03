/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2014,2015 by Peter Pilgrim, Addiscombe, Surrey, XeNoNiQUe UK
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU GPL v3.0
 * which accompanies this distribution, and is available at:
 * http://www.gnu.org/licenses/gpl-3.0.txt
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/

package uk.co.xenonique.client.acme

import com.sun.jersey.api.client.{Client, WebResource}
import org.junit.runner.RunWith
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar

/**
 * Verifies the operation of the ACMEClientResourceSpec
 *
 * @author Peter Pilgrim
 */

@RunWith(classOf[JUnitRunner])
class ACMEClientResourceSpec extends FlatSpec with BeforeAndAfterAllConfigMap
with  BeforeAndAfterEach with MockitoSugar with Matchers {

  var clientResource: ACMEClientResource = _
  var client: Client = _
  var resource: WebResource = _
  var rawJsonText: String = _

  override def beforeAll(configMap: ConfigMap) {
    rawJsonText = readCapturedFile()
  }

  def readCapturedFile(): String = {
    import scala.io.Source

    val CAPTURED_FILENAME = "ACME-CAPTURED.json"
    val buffer = new StringBuilder()
    for (line <- Source.fromFile(CAPTURED_FILENAME).getLines()) {
      buffer.append(line)
      buffer.append("\n")
    }
    buffer.toString()
  }

  override def beforeEach() {
//    println("beforeEach()")
    // Create a mock version of the client resource, the `static factory' method is replaced with a mock

    resource = mock[WebResource]
    client = mock[Client]

    clientResource = new ACMEClientResource {
      override def createClient(): Client = client
    }

    when(client.resource(anyString())).thenReturn(resource)
    when(resource.path(anyString())).thenReturn(resource)
    when(resource.queryParam(anyString(),anyString())).thenReturn(resource)
    when(resource.queryParam(anyString(),anyString())).thenReturn(resource)
    when(resource.queryParam(anyString(),anyString())).thenReturn(resource)

    when(resource.get(classOf[String])).thenReturn(rawJsonText)
  }

  override def afterEach() {
//    println("afterEach()")
  }

  "Resource" should "should be retrieve data" in {
//    val clientResource = new ACMEClientResource()
    val result = clientResource.executeRequest()
    result.length() should be > 0

    // Verify the operation of the instance
    verify(client).resource(anyString())
    verify(resource).path(anyString())
    verify(resource, times(3)).queryParam(anyString(),anyString())
    verify(resource).get(classOf[String])
  }


  "Resource" should "should parse raw string into JSON" in {
//    val clientResource = new ACMEClientResource()
    val json = clientResource.executeRequestReturnJSON()
    (json != null) should be (true)

    // Verify the operation of the instance
    verify(client).resource(anyString())
    verify(resource).path(anyString())
    verify(resource, times(3)).queryParam(anyString(),anyString())
    verify(resource).get(classOf[String])
  }


  "Resource" should "combine term search query terms" in {
    val clientResource = new ACMEClientResource()
    val fruits = List("Apple", "Orange", "Pear", "Strawberry")
    val terms = List("ORANGE", "pEAr")
    clientResource.containsTerms(fruits, terms) should be (true)
    clientResource.containsTerms(fruits, terms.reverse) should be (true)
  }

  "Resource" should "combine not insufficient search query terms" in {
    val clientResource = new ACMEClientResource()
    val fruits = List("Apple", "orange", "PEAR", "Strawberry")
    val terms = List("Orange", "Kiwi")
    clientResource.containsTerms(fruits, terms) should be (false)
    clientResource.containsTerms(fruits, terms.reverse) should be (false)
  }

  "Resource" should "should be filter JSON by single term" in {
    //    val clientResource = new ACMEClientResource()
    val products = clientResource.executeRequestFilterByProduct( List("Striped"))
    products.size should be (43)
  }


  "Resource" should "should be filter JSON by two terms" in {
    //    val clientResource = new ACMEClientResource()
    val products = clientResource.executeRequestFilterByProduct(List("Striped", "Cotton"))
    products.size should be(19)
  }

  "Resource" should "should be filter JSON and pretty print" in {
    val products = clientResource.executeRequestFilterByProduct(List("Striped", "Cotton"))
    val expectedJsonRawSubText = """[{"product-id":"493228","name":"Striped cotton T-shirt ","price":105}"""
    clientResource.convertProductsToRawJsonText(products).substring(0,expectedJsonRawSubText.length()) should be ( expectedJsonRawSubText)
  }
}

