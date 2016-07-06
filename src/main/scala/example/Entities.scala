package example

import scala.beans.BeanProperty
import com.googlecode.objectify.annotation.{ Entity, Id }

@Entity
case class User1( @BeanProperty @Id ident: String, name: String, phone: String)

@Entity 
class User{

	@BeanProperty @Id 
	var ident: String = _

	@BeanProperty 
	var name: String = _

	@BeanProperty 
	var phone: String = _

}

object User {
	def apply(ident1: String, name1: String, phone1: String): User = {
		val u = new User()
    u.ident = ident1
    u.name = name1
		u.phone = phone1
		u
  }  
}
