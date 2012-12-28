/*
 * Copyright 2001-2009 Artima, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package samples

/*
ScalaTest facilitates different styles of testing by providing traits you can mix
together to get the behavior and syntax you prefer.  A few examples are
included here.  For more information, visit:

http://www.scalatest.org/

One way to use ScalaTest is to help make JUnit or TestNG tests more
clear and concise. Here's an example:
*/

import configurations.{JedisConfiguration, MongoConfiguration, TutuluConfiguration}
import org.scalatest._
import junit.JUnitRunner
import org.specs.specification.DefaultExampleExpectationsListener
import org.junit.runner.RunWith
import dw_server.models.{VariantList, KudoData, ISBN, Kudo}
import org.codehaus.jackson.map.ObjectMapper
import java.io.File
import com.yammer.dropwizard.testing.JsonHelpers.{asJson,fromJson,jsonFixture}
import com.yammer.dropwizard.config.{Bootstrap, Environment}
import dw_server.services.TutuluService
import dw_server.resources.{SearchKudosResource, AddKudosResource}
import com.yammer.dropwizard.testing.ResourceTest
import net.vz.mongodb.jackson.JacksonDBCollection
import org.junit._
import com.google.common.collect.Maps
import java.util

//TODO
@RunWith(classOf[JUnitRunner])
class TutuluPOJOSuit extends FunSuite{

  val mapper = new ObjectMapper()
  //test POJO: Kudos
  test("isbn.json is properly mapped to ISBN class: deseri/serialization ") {
    val isbn_test=new ISBN("1234567890","1234567890123")
    val isbn:ISBN = mapper.readValue(new File("src/test/resources/isbn.json"),classOf[ISBN])
      assert(isbn.getI_10 == isbn_test.i_10)
      assert(isbn.getI_13 == (isbn_test.i_13))
      //jsonFixture only search file inside src/test/resources
      //deserialization

      println("serial: %s".format(asJson(isbn_test)))
      println("fixture: %s".format(jsonFixture("isbn.json")))
      assert( isbn_test == fromJson(jsonFixture("isbn.json"),classOf[ISBN]))
      //serialization
      assert(asJson(isbn_test) == jsonFixture("isbn.json"))
  }

  test( "kudo.json is properly mapped to Kudo class") {
     val kudo:Kudo = mapper.readValue(new File("src/test/resources/kudos.json"),classOf[Kudo])
     val kudo_test = new Kudo()
     kudo_test.data=new KudoData("lucid1.0","Sword Art Online","Light Novel")
     kudo_test.data.author="Reki Kawahara"
     val tempAL=new util.ArrayList[KudoData]
     tempAL.add({
       val v1=new KudoData("lucid1.1","Sword Art Online 1: Aincrad","Light Novel")
       v1.isbn=new ISBN("","978-4-04-867760-8")
       v1.attributes=Maps.newHashMap()
       v1
     })
     tempAL.add({
      val v1=new KudoData("lucid1.2","Sword Art Online 2: Aincrad","Light Novel")
      v1.isbn=new ISBN("","978-4-04-867935-0")
      v1.attributes=Maps.newHashMap()
      v1.attributes.put("description","hello")
      v1
     })
     kudo_test.variants=new Array[KudoData](tempAL.size)
     tempAL.toArray[KudoData](kudo_test.variants)

     println("serial: %s".format(asJson(kudo_test)))
     println("fixture: %s".format(jsonFixture("kudos.json")))
     //deseria;
     assert( kudo_test == fromJson(jsonFixture("kudos.json"),classOf[Kudo]))
     //serialization
     assert(asJson(kudo_test) == jsonFixture("kudos.json"))

  }
  test("kudoData.json is properly mapped to KudoData class"){
      val kd:KudoData= mapper.readValue(new File("src/test/resources/kd.json"),classOf[KudoData])
      val kd_test = new KudoData("lucid1.1", "Sword Art Online 1: Aincrad",
                    "Light Novel")

      kd_test.isbn=new ISBN("","978-4-04-867760-8")
      assert(kd.getLucid === kd_test.getLucid)
      assert(kd.getName == kd_test.getName)
      assert(kd.getCategory == kd_test.getCategory)

      //deserialization
      println("serial: %s".format(asJson(kd_test)))
      println("fixture: %s".format(jsonFixture("kd.json")))
      assert( kd_test == fromJson(jsonFixture("kd.json"),classOf[KudoData]))
      //serialization
      assert(asJson(kd_test) == jsonFixture("kd.json"))


  }
}



@RunWith(classOf[JUnitRunner])
class TutuluServiceSuit extends WordSpec with org.specs.mock.Mockito with DefaultExampleExpectationsListener
                                         with BeforeAndAfter{
 //test TutuluService
  var env:Environment = mock[Environment]
  var config:TutuluConfiguration = new TutuluConfiguration

  before {
          config.mongoC=new MongoConfiguration()
          config.jedisC=new JedisConfiguration()

  }

  "AddKudoResource, SearchKudosResource" should {
    "be added to TutuluService" in{
      TutuluService.run(config,env)
      there was atLeastOne(env).addResource(any[AddKudosResource])
      there was one(env).addResource(any[SearchKudosResource])

    }
  }
}


//Junit
class TutuluResourceSuit extends ResourceTest{

    val mapper = new ObjectMapper()
    val kudo = mapper.readValue(new File("src/test/resources/kudos.json"),classOf[Kudo])
    var kudos =org.mockito.Mockito.mock(classOf[JacksonDBCollection[Kudo,String]])


    override def setUpResources() {

//      org.mockito.Mockito.when(kudos.find().next()).thenReturn(kudo)
//      addResource(new AddKudosResource(kudos));

    }

}