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

import org.scalatest._
import junit.JUnitRunner
import org.specs.mock.Mockito
import org.specs.specification.DefaultExampleExpectationsListener
import org.junit.runner.RunWith
import dw_server.models.{ISBN, Kudo}


//class StackSuite extends Assertions {
//
//  @Test def stackShouldPopValuesIinLastInFirstOutOrder() {
//    val stack = new Stack[Int]
//    stack.push(1)
//    stack.push(2)
//    assert(stack.pop() === 2)
//    assert(stack.pop() === 1)
//  }
//
//  @Test def stackShouldThrowNoSuchElementExceptionIfAnEmptyStackIsPopped() {
//    val emptyStack = new Stack[String]
//    intercept[NoSuchElementException] {
//      emptyStack.pop()
//    }
//  }
//}
//
//import org.junit.runner.RunWith
//import org.scalatest.junit.JUnitRunner
//@RunWith(classOf[JUnitRunner])
//class ExampleSuite extends WordSpec with Mockito with DefaultExampleExpectationsListener{
//
//    "it" should {
//      "be possible to use the Mockito trait from specs" in {
//        val m = mock[java.util.List[String]]
//        m.get(0) returns "one"
//        m.get(0)
//        there was one(m).get(0)
//      }
//    }
////
////   test( "pop is invoked on noempty") {
////    val stack = new Stack[Int]
////    stack.push(1)
////    stack.push(2)
////    val result = stack.pop()
////    assert(result === 2)
////  }
//
//}


//TODO
@RunWith(classOf[JUnitRunner])
class TutuluServiceSuit extends WordSpec with Mockito with DefaultExampleExpectationsListener
                                         with BeforeAndAfter{

  /**var config:TutuluConfiguration = _
  var service:ScalaService[TutuluConfiguration] =_
  var mdb:Mongo=_
  var jedb:JedisPool=_
  **/

  before{
  }

  //test POJO: Kudos
  "isbn" should {
      "should respect format of isbn_10, isbn_13" in {
          val isbn = new ISBN("0000000000", "0000000000")
      }
  }

  "kudo" should {
    "" in {

     }
  }
  "kudoData" should {
    "" in {

    }

  }

  //test TutuluService



}