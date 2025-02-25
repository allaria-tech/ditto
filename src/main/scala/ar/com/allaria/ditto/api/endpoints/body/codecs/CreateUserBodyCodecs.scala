package ar.com.allaria.ditto.api.endpoints.body.codecs

import ar.com.allaria.ditto.api.endpoints.body.CreateUserBody
import daruma.commons.api.codecs.SnakeCaseAutoDerivation
import io.circe.{Decoder, Encoder}

object CreateUserBodyCodecs extends SnakeCaseAutoDerivation {

  implicit val createUserBodyEncoder: Encoder[CreateUserBody] =
    exportEncoder[CreateUserBody].instance

  implicit val createUserBodyDecoder: Decoder[CreateUserBody] =
    exportDecoder[CreateUserBody].instance

}
