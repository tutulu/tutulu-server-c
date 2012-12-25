/**
 * Created with IntelliJ IDEA.
 * User: kira
 * Date: 12/24/12
 * Time: 6:56 PM
 * To change this template use File | Settings | File Templates.
 */
package dw_server.models

import net.vz.mongodb.jackson.{Id, ObjectId}
import javax.validation.Valid
import org.hibernate.validator.constraints.NotEmpty
import com.google.common.collect.Maps
import java.util


//when in doubt, check POJO format reference:
//http://www.bigbeeconsultants.co.uk/blog/easy-pojos-in-scala

class Kudo{
  @Id @ObjectId private[this] var mid:String=_
  @Valid private[this] var mdata:KudoData=_
  @Valid private[this] var mvariants:List[Any]=_
  //getter
  def id =mid
  def data = mdata
  def variants = mvariants
  //setter
  def id_=(s:String) = { mid = s}
  def data_=(d:KudoData) = { mdata= d}
  def variants_=(v:List[Any]) = {mvariants=v}
}

case class ISBN(i_13:String,i_10:String)
class KudoData{
  @NotEmpty var isbn:ISBN=_
  @NotEmpty var name:String=_
  @NotEmpty var category:String=_
  var author:String=_
  var attributes:util.HashMap[Nothing, Nothing]=Maps.newHashMap()
}


