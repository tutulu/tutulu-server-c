package dw_server.utils

import javax.ws.rs.{core, WebApplicationException}
import core.Response.Status
import net.vz.mongodb.jackson.DBCursor
import scala.throws

object ResourceHelper {

  @throws(classOf[WebApplicationException])
  def notFoundIfNull(obj:AnyRef){
        errorIfNull(obj,Status.NOT_FOUND)
        if(obj.isInstanceOf[DBCursor[Any]]){
            val cursor:DBCursor[Any] = obj.asInstanceOf[DBCursor[Any]]
            if (!cursor.hasNext) throw new WebApplicationException(Status.NOT_FOUND)
        }
  }

  @throws(classOf[WebApplicationException])
  def errorIfNull(obj:AnyRef, status:Status){
        if (obj == null){
                  throw new WebApplicationException(status)
        }
      }

}
