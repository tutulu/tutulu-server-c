/**
 * Created with IntelliJ IDEA.
 * User: kira
 * Date: 12/24/12
 * Time: 11:16 AM
 * To change this template use File | Settings | File Templates.
 */
package dw_server.resources

import com.yammer.metrics.annotation.Timed
import javax.ws.rs._
import core.Response.Status
import core.{Response, MediaType}
import java.util.concurrent.atomic.AtomicLong
import net.vz.mongodb.jackson.{DBCursor, JacksonDBCollection}
import dw_server.models.{testJson, Kudo}
import javax.validation.Valid
import dw_server.utils.ResourceHelper

@Path("/hello_word")
@Produces(Array(MediaType.APPLICATION_JSON))
class TutuluResource(val defaultName:String, val template:String){
  val counter = new AtomicLong(0)

  @GET
  @Timed
  def sayTutulu(@QueryParam("name") name: Option[String]): String = {
    counter.incrementAndGet()
    return String.format(template,name.getOrElse(defaultName))
  }

}


@Path("/addKudo")
@Produces(Array("application/json"))
@Consumes(Array("application/json"))
class AddKudosResource(val kudos:JacksonDBCollection[Kudo,String]){

  @PUT
  def createKudo(@Valid k:Kudo):Response={
      ResourceHelper notFoundIfNull(k)
      val cursor: DBCursor[Kudo] = kudos.find().is("data.lucid", (k.getData).getLucid)
      if (cursor.hasNext){
          return Response.status(Status.CONFLICT).build()
      }
      kudos.save(k)

      return Response.noContent().build()
  }

}

@Path("/searchKudo/{id}")
@Produces(Array(MediaType.APPLICATION_JSON))
class SearchKudosResource(val kudos:JacksonDBCollection[Kudo,String]){

  @GET
  @Timed
  def getKudos(@PathParam("id") id:String):Kudo={
      val cursor: DBCursor[Kudo] =kudos.find().is("data.lucid", id)
      ResourceHelper notFoundIfNull (cursor)
      return cursor.next
  }
}



