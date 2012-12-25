/**
 * Created with IntelliJ IDEA.
 * User: kira
 * Date: 12/24/12
 * Time: 11:21 PM
 * To change this template use File | Settings | File Templates.
 */
package dw_server.services

import com.yammer.dropwizard.lifecycle.Managed
import com.mongodb.Mongo
import redis.clients.jedis.JedisPool

class MongoManaged(val m:Mongo) extends Managed{
  def start() {}
  def stop() { m.close()}
}


class JedisManaged(val jp:JedisPool) extends Managed{
  def start() {}
  def stop() { jp.destroy()}
}
