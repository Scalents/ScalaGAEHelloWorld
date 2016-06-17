package example

import javax.servlet.http.{ HttpServlet, HttpServletRequest, HttpServletResponse } 

class HelloServlet extends HttpServlet {

  override def doGet( request: HttpServletRequest, response: HttpServletResponse ): Unit = response.getWriter.println("Hello from Scala in GAE!") 

}
