package ar.com.allaria.ditto.api.endpoints.response.user.codecs

import ar.com.allaria.ditto.api.endpoints.response.user.UserCreateResponse
import daruma.commons.api.codecs.SnakeCaseAutoDerivation
import io.circe.{Decoder, Encoder}

object UserCreateResponseCodec extends SnakeCaseAutoDerivation {

  implicit val alarmResponseEncoder: Encoder[UserCreateResponse] =
    exportEncoder[UserCreateResponse].instance

  implicit val alertResponseDecoder: Decoder[UserCreateResponse] =
    exportDecoder[UserCreateResponse].instance
}
