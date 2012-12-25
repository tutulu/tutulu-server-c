package configurations

import com.yammer.dropwizard.config.Configuration
import org.hibernate.validator.constraints.NotEmpty
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.{NotNull, Min, Max}
import javax.validation.Valid

/**
 * Created with IntelliJ IDEA.
 * User: kira
 * Date: 12/24/12
 * Time: 2:43 PM
 */

class TutuluConfiguration extends Configuration{
  @NotEmpty
  @JsonProperty
  val template:String="Tutulu  %s" // all value are ~ public in scala, val = immutable variable

  @NotEmpty
  @JsonProperty
  val defaultName:String = "Stranger"

  //test
  @Valid
  @NotNull
  @JsonProperty
  var mongoC:MongoConfiguration=_

  //test
  @Valid
  @NotNull
  @JsonProperty
  var jedisC:JedisConfiguration=_

//  @Valid
//  @NotNull
//  @JsonProperty
//  var jedis:JedisConfiguration=_

}
//test
class MongoConfiguration{
  @JsonProperty
  @NotEmpty
  val mongohost:String="localhost"

  @Min(1)
  @Max(65535)
  @JsonProperty
  var mongoport:Int=27017

  @JsonProperty
  @NotEmpty
  val mongodb:String="scaladb"
}

//jedis db configuration
class JedisConfiguration{
  @JsonProperty
  @NotEmpty
  val jedishost:String="localhost"

}

