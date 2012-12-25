package dw_server.services

import com.yammer.dropwizard.ScalaService
import com.yammer.dropwizard.bundles.ScalaBundle
import com.yammer.dropwizard.config.{Environment, Bootstrap}
import configurations.TutuluConfiguration
import dw_server.resources._
import dw_server.health.{JedisHealthCheck, MongoHealthCheck, TutuluHealthCheck}
import com.mongodb.{DB, Mongo}
import net.vz.mongodb.jackson.JacksonDBCollection
import dw_server.models.Kudo
import redis.clients.jedis.{Jedis, JedisPoolConfig, JedisPool}

/**
 * @author ${user.name}
 */



object TutuluService extends ScalaService[TutuluConfiguration] {
   def initialize(bootstrap:Bootstrap[TutuluConfiguration]){
     bootstrap.setName("hello_world")
     bootstrap.addBundle(new ScalaBundle)
   }

   def run(configuration:TutuluConfiguration, environment:Environment){

     //mongodb setup
     val mongo= new Mongo(configuration.mongoC.mongohost,
                             configuration.mongoC.mongoport)
     val mdb:DB = mongo.getDB(configuration.mongoC.mongodb)
     val kudos:JacksonDBCollection[Kudo,String] = JacksonDBCollection.wrap(
                                                  mdb.getCollection("kudos"),
                                                  classOf[Kudo],
                                                  classOf[String])
     val mongoM = new MongoManaged(mongo)
     environment.manage(mongoM)
     environment.addHealthCheck(new MongoHealthCheck(mongo))

     //jedis setup
     val pool = new JedisPool(new JedisPoolConfig(),configuration.jedisC.jedishost)
     //val jedis:Jedis = pool.getResource()
     //pool.returnResource(jedis)
     val jedisM = new JedisManaged(pool)
     environment.manage(jedisM)
     environment.addHealthCheck(new JedisHealthCheck(pool))

     //searchKudos
     environment.addResource(new SearchKudosResource(kudos))
     environment.addResource(new AddKudosResource(kudos))
     environment.addResource(new RemoveKudosResource(kudos))

     //sampleResource
     environment.addResource(new TutuluResource(configuration.defaultName, configuration.template))
     environment.addHealthCheck(new TutuluHealthCheck(configuration.template))
   }
}


