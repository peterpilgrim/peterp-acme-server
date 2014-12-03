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

/**
 * The type HelloWorldResource
 *
 * @author Peter Pilgrim
 */
import java.util.concurrent.atomic.AtomicLong
import javax.ws.rs._
import javax.ws.rs.core.{MediaType, Response}

import com.codahale.metrics.annotation.Timed
import com.google.common.base.Optional

@Path("/")
@Produces(Array(MediaType.APPLICATION_JSON))
class ACMEResource( template: String, defaultName: String ) {
  private val counter =  new AtomicLong(0)


  @GET
  @Timed
  def executeQuery( @QueryParam("q") searchTerms: Optional[String]): Response = {
      if ( searchTerms.isPresent()) {
        val clientResource = new ACMEClientResource()
        val terms = searchTerms.get().split("[ \t]+").toList
        val products = clientResource.executeRequestFilterByProduct( terms )
        val rawJsonText = clientResource.convertProductsToRawJsonText(products)
        Response.ok(rawJsonText).header("content-type", "application/json").build()
      }
      else {
        Response.status(Response.Status.NOT_FOUND).build()
      }
  }

  @Path("/hello-world")
  @GET
  @Timed
  def sayHello( @QueryParam("name") name: Optional[String]): Saying = {
      val value = String.format(template, name.or(defaultName))
      return new Saying(counter.incrementAndGet(), value)
  }
}
