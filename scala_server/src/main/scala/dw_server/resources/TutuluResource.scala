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
import javax.ws.rs.core.MediaType
import java.util.concurrent.atomic.AtomicLong
import net.vz.mongodb.jackson.JacksonDBCollection
import dw_server.models.Kudo

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
@Produces(Array(MediaType.APPLICATION_JSON))
class AddKudosResource(val kudos:JacksonDBCollection[Kudo,String]){



}

@Path("/removeKudo")
@Produces(Array(MediaType.APPLICATION_JSON))
class RemoveKudosResource(val kudos:JacksonDBCollection[Kudo,String]){

}

@Path("/searchKudo")
@Produces(Array(MediaType.APPLICATION_JSON))
class SearchKudosResource(val kudos:JacksonDBCollection[Kudo,String]){

  @GET
  @Timed
  def getKudos(){

  }
}



