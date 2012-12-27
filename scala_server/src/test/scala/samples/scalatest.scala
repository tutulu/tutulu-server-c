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

import configurations.TutuluConfiguration
import org.scalatest._
import junit.JUnitRunner
import org.specs.mock.Mockito
import org.specs.specification.DefaultExampleExpectationsListener
import org.junit.runner.RunWith
import dw_server.models.{KudoData, ISBN, Kudo}
import org.codehaus.jackson.map.ObjectMapper
import java.io.File
import com.yammer.dropwizard.testing.JsonHelpers.asJson
import com.yammer.dropwizard.testing.JsonHelpers.jsonFixture
import com.yammer.dropwizard.config.Environment

//TODO
@RunWith(classOf[JUnitRunner])
class TutuluPOJOSuit extends FunSuite{

  val mapper = new ObjectMapper()
  //test POJO: Kudos
  test("isbn.json is properly mapped to ISBN class") {
      val isbn:ISBN = mapper.readValue(new File("src/test/scala/test_json/isbn.json"),classOf[ISBN])
      assert(isbn.getI_10 === "1234567890")
      assert(isbn.getI_13 === "1234567890123")
      //println(asJson(jsonFixture("./src/test/scala/test_json/isbn.json"),classOf[ISBN]))

  }

  test( "kudo.json is properly mapped to Kudo class") {
     // val kudo:Kudo = mapper.readValue(new File("src/test/scala/test_json/kudos.json"),classOf[Kudo])

  }
  test("kudoData.json is properly mapped to KudoData class"){
      // val kd:KudoData= mapper.readValue(new File("src/test/scala/test_json/kd.json"),classOf[KudoData])

  }
}

//TODO ; check dropwizard-core for service testing
@RunWith(classOf[JUnitRunner])
class TutuluServiceSuit extends WordSpec with Mockito with DefaultExampleExpectationsListener
                                         with BeforeAndAfter{
 //test TutuluService
  var env:Environment = mock[Environment]
  var config:TutuluConfiguration = new TutuluConfiguration

  before {
   // def setup={//setup custom configuration}
  }

}