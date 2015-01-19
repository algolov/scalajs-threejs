package server

import akka.actor.ActorSystem
import akka.http.server.directives.AuthenticationDirectives._
import akka.http.Http
import akka.http.server.Directives
import akka.stream.FlowMaterializer
import akka.util.Timeout
import akka.http.unmarshalling._

import akka.stream.scaladsl.Sink

import scala.concurrent.duration._

import akka.http.model.{ HttpEntity, MediaTypes }

import reacttest.FileData

object HttpServer extends App {
  
  implicit val system = ActorSystem()
  implicit val materializer = FlowMaterializer()
  implicit val ec = system.dispatcher
  implicit val askTimeout: Timeout = 500.millis
  
  
  import Directives._

  val binding = Http().bind(interface = "localhost", port = 8080)

  val materializedMap = binding startHandlingWith {
    get {
      path("threejs") {
        complete {
          HttpEntity (
            MediaTypes.`text/html`,
            Page.htmlContent("Threejs")
          )
        }
      } ~
      getFromResourceDirectory("")
    }
  }

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  Console.readLine()
  
  binding.unbind(materializedMap).onComplete(_ ⇒ system.shutdown())
  
}
