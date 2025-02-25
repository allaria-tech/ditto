package ar.com.allaria.ditto.api.endpoints.body


import ar.com.allaria.ditto.api.endpoints.body.codecs.CreateUserBodyCodecs._
import sttp.tapir.EndpointIO.Example
import sttp.tapir.EndpointInput
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe.jsonBody

final case class CreateUserBody(
    name: String,
    email: String
)

object CreateUserBody {

  private val userCreateBody: Example[CreateUserBody] = Example(
    value = CreateUserBody(name = "some name", email = "someemail@some.com"),
    name = Some("Some user"),
    summary = Some("Some user information")
  )

  def createUserBody: EndpointInput[CreateUserBody] =
    jsonBody[CreateUserBody].example(userCreateBody)
}
