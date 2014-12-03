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

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

/**
 * The type TemplateHealthChecks
 *
 * @author Peter Pilgrim
 */

class TemplateHealthCheck(val template: String) extends HealthCheck {

  override def check(): Result = {
    val saying = String.format(template, "TEST")
    if (!saying.contains("TEST")) {
      Result.unhealthy("template doesn't include a name")
    }
    else {
      Result.healthy()
    }
  }
}