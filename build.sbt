import scalajs.sbtplugin.ScalaJSPlugin._
import ScalaJSKeys._

lazy val sharedSettings = Seq(
  unmanagedSourceDirectories in Compile +=
    baseDirectory.value / "shared" / "main" / "scala",
  libraryDependencies ++= Seq(
      "com.scalatags" %%% "scalatags" % "0.4.2"
    , "com.lihaoyi" %%% "upickle" % "0.2.5"
    , "com.lihaoyi" %%% "autowire" % "0.2.3"
  ),
  scalaVersion := "2.11.5",
  scalacOptions ++= Seq("-deprecation", "-feature")
)

lazy val client = project.in(file("client"))
  .settings(scalaJSSettings: _*)
  .settings(sharedSettings: _*)
  .settings(bintraySettings:_*)
  .settings(
    resolvers += bintray.Opts.resolver.repo("denigma", "denigma-releases"),
    libraryDependencies ++= Seq(
        "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6"
      , "org.scalajs" %%% "threejs" % "0.0.68-0.1.1"
    ),
    ScalaJSKeys.jsDependencies ++= Seq(
      "org.webjars" % "three.js" % "r68" / "three.js" commonJSName "Three",
      scala.scalajs.sbtplugin.RuntimeDOM),
    // creates single js resource file for easy integration in html page
    skip in packageJSDependencies := false
  )

lazy val server = project.in(file("server"))
  .settings(sharedSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor" % "2.3.8"
      , "com.typesafe.akka" % "akka-stream-experimental_2.11" % "1.0-M2"
      , "com.typesafe.akka" % "akka-http-experimental_2.11" % "1.0-M2"
      , "com.typesafe.akka" % "akka-http-core-experimental_2.11" % "1.0-M2"
    ),
    (resources in Compile) += {
      (fastOptJS in (client, Compile)).value
      (artifactPath in (client, Compile, fastOptJS)).value
    },
    (resources in Compile) += {
      (packageJSDependencies in (client, Compile)).value
      (artifactPath in (client, Compile, packageJSDependencies)).value
    }
  )