/**
 * Created with IntelliJ IDEA.
 * User: kira
 * Date: 12/24/12
 * Time: 11:42 AM
 * To change this template use File | Settings | File Templates.
 */
package dw_server.health

import com.yammer.metrics.core.HealthCheck
import javax.ws.rs.GET
import com.yammer.metrics.core.HealthCheck.Result
import com.mongodb.Mongo
import redis.clients.jedis.{JedisPool, Jedis}
import java.io.{PrintWriter, StringWriter}


class TutuluHealthCheck(name:String) extends HealthCheck("template"){//? super here?
  val template:String = name
  @GET
  def check: Result ={
    val saying:String=String.format(template,"TEST")
    if (!saying.contains("TEST")){
      return Result.unhealthy("template doesn't include a name")
    }
    return Result.healthy()
  }
}

//jedis,mongo,
class MongoHealthCheck(val mdb:Mongo) extends HealthCheck("mongo database"){
  @GET
  def check:Result={
    try{
      mdb.getDatabaseNames //TODO
    }catch{
      case e =>{
        val sw=new StringWriter()
        val pw = new PrintWriter(sw)
        e.printStackTrace(pw)
        return Result.unhealthy( "[MONGODB CONNECTION FAILURE]:cannot get database name: %s" .format(sw.toString))
      }
    }
    return Result.healthy()
  }
}
/**test that you are able to get a thread from thread pool*/
class JedisHealthCheck(jp:JedisPool) extends HealthCheck("jedis database"){


  @GET
  def check:Result={
    var jedis:Jedis=null

    try{
         jedis = jp.getResource
         jedis.set("status","healthy")
         val status:String = jedis.get("status")
         if(status!="healthy")
            return Result.unhealthy("[JEDIS ERROR]: cannot get right value back")
    }catch {
      case e => {
        val sw = new StringWriter()
        val pw = new PrintWriter(sw)
        e.printStackTrace(pw)
        return Result.unhealthy("[ JEDIS CONNECTION FAILURE]: %s".format(sw.toString))
      }
    } finally {
        if(jedis!=null)
          jp.returnResource(jedis)
    }
    return Result.healthy()
  }
}



