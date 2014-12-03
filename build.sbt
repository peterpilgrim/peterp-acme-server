// Build file for Peter Pilgrim ACME server

// Define the project name
name := "peterp-acme-server"

// Define the version
version := "1.0-SNAPSHOT"

// What version of Scala do we need?
scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
   "com.massrelevance" % "dropwizard-scala_2.11" % "0.7.0", 
   "org.joda" % "joda-convert" % "1.7",
   "joda-time" % "joda-time" % "2.5",
   "com.sun.jersey" % "jersey-client" % "1.18",
   "org.json4s" %% "json4s-native" % "3.2.10",
//   "org.json4s" %% "json4s-jackson" % "3.2.10",
//   "org.glassfish.jersey.core" % "jersey-client" % "2.0",
   "junit" % "junit" % "4.11" % "test",
   "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test",
   "com.novocode" % "junit-interface" % "0.11" % "test->default",
   "org.mockito" % "mockito-all" % "1.10.8" % "test"
)


mainClass in (Compile,run) := Some("uk.co.xenonique.client.acme.ACMEApplication")

// fork a new JVM for 'run' and 'test:run'
fork := true

// fork a new JVM for 'test:run', but not 'run'
fork in Test := true

// add a JVM option to use when forking a JVM for 'run'
javaOptions += "-Xmx512M"

