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

@Api( name = "myService" , version = "v1" )
class ApiEndpoints {

  {
		//not the right place, but for a demo is ok
		//https://github.com/objectify/objectify/wiki/BestPractices
		ObjectifyService.register(classOf[User])
  }


  @ApiMethod( name = "sayHi" )
  def sayHi(@Named("name") name: String ) = Response(s"Hi, ${name}!")

  @ApiMethod( name = "user.create" )
  def createUser( @Named("name") name: String, @Named("phone") phone: String ): Response = {
    val user = User(java.util.UUID.randomUUID.toString, name, phone)
    ApiEndpoints.log.info(s"Saving ${user} ") 
    val key = ofy().save().entity(user).now()
    Response(key.toString)
  }


  @ApiMethod( name = "user.find" )
  def findUser( @Named("id") id: String ): Response = {
    ApiEndpoints.log.info(s"Finding user ${id}") 
    val user = ofy().load().`type`(classOf[User]).id(id).now()
    Response(Option(user).toString)
  }

}

object ApiEndpoints {
  
  val log: Logger = Logger.getLogger(classOf[ApiEndpoints].toString)

}

case class Response(@BeanProperty txt: String)
