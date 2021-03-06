package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

object WebServer extends App {
  implicit val system = ActorSystem("akka-http-sample")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route =
    path("hello") {
      get {
        complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Hello Akka-http!</h1>"))
      }
    }

  Http().bindAndHandle(route, "0.0.0.0", 6060)
}
