// http://mvnrepository.com/artifact/javax.servlet/javax.servlet-api

val gaeVersion = "1.9.36"

lazy val cloudEndpoints = taskKey[Unit]("Execute shell script to generate cloud endpoints")

cloudEndpoints := {
    "./endpoints.sh get-client-lib" !
  }

libraryDependencies ++= Seq(
    "javax.servlet" % "javax.servlet-api" % "3.1.0",
    "com.google.appengine" % "appengine-endpoints" % gaeVersion
)

resolvers += "Typesafe" at "https://repo.typesafe.com/typesafe/releases/"

scalaVersion := "2.11.8"

