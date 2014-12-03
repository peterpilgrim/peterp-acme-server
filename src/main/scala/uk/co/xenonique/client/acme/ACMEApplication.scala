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


import com.massrelevance.dropwizard.ScalaApplication
import io.dropwizard.setup.{Bootstrap, Environment}

/**
 * The type ACME Application
 *
 * @author Peter Pilgrim
 */
object ACMEApplication extends ScalaApplication[ACMEConfiguration] {

  override def getName():String = "nap-applicastion"

  override def initialize(bootstrap: Bootstrap[ACMEConfiguration]) : Unit = {
  }

  override def run(configuration: ACMEConfiguration,
                   environment: Environment): Unit = {
    println("\n++++ Starting application server ++++")
    println(BANNER)
    val resource = new ACMEResource(
      configuration.getTemplate(),
      configuration.getDefaultName()
    )
    val healthCheck = new TemplateHealthCheck(configuration.getTemplate())
    environment.healthChecks().register("template", healthCheck)
    environment.jersey().register(resource)
  }

  private val BANNER =
    """
      |
      |
      |__________        __                    _____  _________     _____  ___________
      |\______   \ _____/  |_  ___________    /  _  \ \_   ___ \   /     \ \_   _____/
      | |     ___// __ \   __\/ __ \_  __ \  /  /_\  \/    \  \/  /  \ /  \ |    __)_
      | |    |   \  ___/|  | \  ___/|  | \/ /    |    \     \____/    Y    \|        \
      | |____|    \___  >__|  \___  >__|    \____|__  /\______  /\____|__  /_______  /
      |               \/          \/                \/        \/         \/        \/
      |  _________
      | /   _____/ ______________  __ ___________
      | \_____  \_/ __ \_  __ \  \/ // __ \_  __ \
      | /        \  ___/|  | \/\   /\  ___/|  | \/
      |/_______  /\___  >__|    \_/  \___  >__|
      |        \/     \/                 \/
      |
      |
      |   (CC) Peter Pilgrim, XeNoNiQUe UK, November, 2014
      |    Email: peter.pilgrim@gmail.com
      |     Blog: http://xenonique.co.uk/blog
      |
    """.stripMargin
}


