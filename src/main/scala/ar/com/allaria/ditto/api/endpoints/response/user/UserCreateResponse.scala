package ar.com.allaria.ditto.api.endpoints.response.user

import ar.com.allaria.ditto.api.endpoints.response.user.codecs.UserCreateResponseCodec.{alarmResponseEncoder, alertResponseDecoder}
import sttp.tapir.EndpointIO.{Body, Example}
import sttp.tapir.generic.auto.schemaForCaseClass
import sttp.tapir.json.circe.jsonBody

final case class UserCreateResponse(
    id: String,
    name: String,
    email: String
)

object UserCreateResponse {

  private val newUserResponse: Example[UserCreateResponse] = {
    Example(
      value = UserCreateResponse(
        id = "1",
        name = "some user name",
        email = "someuseremail@some.com"
      ),
      name = Some("Created User"),
      summary = Some("New Created User")
    )
  }

  def userCreateResponse: Body[String, UserCreateResponse] =
    jsonBody[UserCreateResponse]
      .example(newUserResponse)
}