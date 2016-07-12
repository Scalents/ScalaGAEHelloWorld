package example

import javax.servlet.http.{ HttpServlet, HttpServletRequest, HttpServletResponse } 

import java.util.logging.Logger


class SchedulerServlet extends HttpServlet {

  override def doGet( request: HttpServletRequest, response: HttpServletResponse ): Unit = {
    SchedulerServlet.log.info(s"Cron scheduled call")
    response.getWriter.println("Scheduled job") 
  }
}

object SchedulerServlet {
  val log: Logger = Logger.getLogger(classOf[SchedulerServlet].toString)
}
