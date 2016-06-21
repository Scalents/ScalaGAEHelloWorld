// http://mvnrepository.com/artifact/javax.servlet/javax.servlet-api
libraryDependencies ++= Seq(
    "javax.servlet" % "javax.servlet-api" % "3.1.0",
    "com.typesafe.akka" %% "akka-http-core" % "2.4.7",
    "com.typesafe.akka" %% "akka-http-experimental" % "2.4.7"
)

resolvers += "Typesafe" at "https://repo.typesafe.com/typesafe/releases/"

scalaVersion := "2.11.8"

