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

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.validator.constraints.Length

import scala.beans.BeanProperty

/**
 * The type Saying
 *
 * @author Peter Pilgrim
 */

class Saying(
              @BeanProperty
              @JsonProperty
              var id: Long,
              @Length(max = 3)
              @BeanProperty
              @JsonProperty
              var content: String) {

  def this(id: Long) = this(id, null)
  // Required for Jackson deserialisation
  def this() = this(0)

}
