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
import org.json4s.JsonAST.JValue
import org.json4s._
import org.json4s.native.JsonMethods._

import scala.collection.immutable.IndexedSeq

//import javax.ws.rs.client.{WebTarget, Client, ClientBuilder}
/**
 * The type ACMEClientResource
 *
 * @author Peter Pilgrim
 */
class ACMEClientResource {

  implicit lazy val formats = DefaultFormats

  val ACME_URL = "http://lad-api.acme-retail-shopping-magazine.com/"
  val ACME_PATH = "product/summaries" // ?business=ACME&categoryIds=2&country=GB"

  /**
   * Factory method to allow overiding in a unit test
   * @return created Jersey object
   */
  def createClient(): Client = {
      Client.create()
  }

  def executeRequest(): String = {
    val client = createClient()
    val resource:WebResource = client.resource(ACME_URL).path(ACME_PATH)
      .queryParam("business", "ACME")
      .queryParam("categoryIds", "2")
      .queryParam("country","GB")

//    println(s"resource=$resource, client=$client")
    val response = resource.get(classOf[String])
//    println(s"response=${response.substring(0,128)}")
    response
  }

  def executeRequestReturnJSON(): JValue = {
    val rawText = executeRequest()
    parse(rawText)
  }

  def executeRequestFilterByProduct( terms: List[String]): List[Product] = {
    val json = executeRequestReturnJSON()

    val z: IndexedSeq[Product] =
      for ( j <- 0 until (json \ "data").children.size ) yield {
        val root = (json \ "data")(j)

        val name = ( root \ "name" \ "en" ).extract[String]
        val productId = ( root \ "id" ).extract[String]
        val gross = ( root \ "price" \ "gross" ).extract[Int]
        val divisor = ( root \ "price" \ "divisor" ).extract[Int]
        val price = gross / divisor
        Product(productId, name, price)
      }

    val u = z filter{ x => containsTerms(x.name.split("[ \\-]+").toList, terms ) }
    u.toList
  }

  def containsTerms( items: List[String], terms: List[String] ): Boolean = {
    val lowercaseItems = items map (_.toLowerCase)
    val x = terms.filter{ term => lowercaseItems.contains(term.toLowerCase) }
    x.size == terms.size
  }

  def convertProductsToRawJsonText( products: List[Product]): String = {
    import org.json4s.native.Serialization
    import org.json4s.native.Serialization.write
    val productSerializer = FieldSerializer[Product](
      FieldSerializer.renameTo("productId", "product-id") )

    implicit val formats = Serialization.formats(NoTypeHints) + productSerializer
    write(products)
  }
}

/**
 * Case class for the product
 * @param productId the product id
 * @param name the product name
 * @param price the product price as gros
 */
case class Product ( productId: String, name: String, price: Int )