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
import org.codehaus.jackson.annotate.JsonProperty
import reflect.BeanProperty
import com.fasterxml.jackson.annotation.JsonCreator


//when in doubt, check POJO format reference:
//http://www.bigbeeconsultants.co.uk/blog/easy-pojos-in-scala

class Kudo{
  @BeanProperty @ObjectId @Id var id:String=_
  @BeanProperty @Valid var data:KudoData=_
  @BeanProperty @Valid var variants:List[Any]=_
}
class ISBN(
  @BeanProperty @JsonProperty("i_10") var i_10:String,
  @BeanProperty @JsonProperty("i_13") var i_13:String
){
  def this()=this("","")
  def this(v:String)=this()
  override def equals(o:Any)= o.isInstanceOf[ISBN] && o.asInstanceOf[ISBN].getI_10==i_10 && o.asInstanceOf[ISBN].getI_13 ==i_13}


class KudoData(
  @BeanProperty @NotEmpty @JsonProperty("lucid") var lucid:String,
  @BeanProperty @NotEmpty @JsonProperty("name") var name:String,
  @BeanProperty @NotEmpty @JsonProperty("category") var category:String
){
  @BeanProperty @JsonProperty("author") var author:String=_
  @BeanProperty @Valid var isbn:ISBN=_
  @BeanProperty @JsonProperty("attributes") var attributes:util.Map[Any,Any]=_

  def this(){this("","","")}

}

/*class testJson{
  @JsonProperty private[this] var text:String=_
  def getText:String = text
  def setText_=(v:String) = {text = v}
}*/

class testJson{
  @JsonProperty var text:String=_
  def getText =text
  def setText_=(v:String)={text=v}
}
