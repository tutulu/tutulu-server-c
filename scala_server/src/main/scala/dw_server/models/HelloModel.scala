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
  @Id @ObjectId var id:String=_
  @Valid var data:KudoData=_
  @Valid var variants:List[Any]=_
}

case class ISBN(i_13:String,i_10:String)
class KudoData{
  @NotEmpty var isbn:ISBN=_
  @NotEmpty var name:String=_
  @NotEmpty var category:String=_
  var author:String=_
  var attributes:util.HashMap[Nothing, Nothing]=Maps.newHashMap()
}


