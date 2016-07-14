package example

import com.google.api.server.spi.auth.common.{ User => AuthUser }
import com.google.api.server.spi.config.{ AnnotationBoolean, Api, ApiMethod, ApiNamespace, ApiResourceProperty } 
import com.google.api.server.spi.response.NotFoundException
import com.google.appengine.api.oauth.OAuthRequestException

import com.google.api.server.spi.config.Named 

import scala.beans.BeanProperty

import java.util.logging.Logger

import com.googlecode.objectify.ObjectifyService
import ObjectifyService.ofy

import com.google.appengine.api.taskqueue.DeferredTask

@Api( name = "tasksService" , version = "v1" )
class Tasks {

  import Tasks._

  def addTask: Unit = {}

  def processTask: Unit = {
    //do pubsub pull here
    
  }

}

class PullTask extends DeferredTask {
  override def run: Unit = {
		Tasks.log.info("Scheduled pull task")
    // expensive operation to be backgrounded goes here
  }
}

import com.google.appengine.api.taskqueue.Queue
import com.google.appengine.api.taskqueue.QueueFactory
import com.google.appengine.api.taskqueue.TaskOptions.Builder._

object Tasks {
  
  val log: Logger = Logger.getLogger(classOf[Tasks].toString)

  def createTasks = {
    val queue = QueueFactory.getDefaultQueue
    queue.add(withCountdownMillis(15*1000).payload(new PullTask()))
    queue.add(withCountdownMillis(2*15*1000).payload(new PullTask()))
    queue.add(withCountdownMillis(3*15*1000).payload(new PullTask()))
  }

}


