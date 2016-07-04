package example

import com.google.api.server.spi.auth.common.User
import com.google.api.server.spi.config.AnnotationBoolean
import com.google.api.server.spi.config.Api
import com.google.api.server.spi.config.ApiMethod
import com.google.api.server.spi.config.ApiNamespace
import com.google.api.server.spi.config.ApiResourceProperty
import com.google.api.server.spi.response.NotFoundException
import com.google.appengine.api.oauth.OAuthRequestException

import com.google.api.server.spi.config.Named 

import scala.beans.BeanProperty

@Api( name = "myService" , version = "v1" )
class ApiEndpoints {

  @ApiMethod( name = "sayHi" )
  def sayHi(@Named("name") name: String ) = Response(s"Hi, ${name}!")


}

case class Response(@BeanProperty txt: String)
