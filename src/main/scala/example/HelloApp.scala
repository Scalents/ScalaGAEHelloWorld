import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.actor._
import akka.stream.scaladsl._
import akka.stream.scaladsl.Flow
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import akka.stream.ActorMaterializer


/**
 * Simple Object that starts an HTTP server using akka-http. All requests are handled
 * through an Akka flow.
 */
object HelloApp extends App {

  // Definition of ActorSystem is required for flowmaterializer and HTTP.
  implicit val system = ActorSystem("hello-world-rest")
  implicit val materializer = ActorMaterializer()

  // start the server on the specified interface and port.
  val bindingServer = Http().bindAndHandleAsync(asyncHandler, "localhost", 8091)

  val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = "http://akka.io"))

def asyncHandler(request: HttpRequest): Future[HttpResponse] = {

    // we match the request, and some simple path checking
    request match {

      case HttpRequest(GET, Uri.Path("/getACK"), _, _, _) => Future(HttpResponse(200, entity = "Hello world ACK"))

      case HttpRequest(GET, Uri.Path("/get"), _, _, _) => {
        // next we match on the query paramter
        request.uri.query().get("name") match {
            case Some(queryParameter) => Future(HttpResponse(200, entity = s"Welcome to Miami %queryParameter"))
            case None => Future(HttpResponse(status = StatusCodes.OK))
          }
      }

      // If case didn't match previous cases, then is a not expecter request: just return a not found
      case HttpRequest(_, _, _, _, _) => {
        Future[HttpResponse] {
          HttpResponse(status = StatusCodes.NotFound)
        }
      }
    }
  }

}