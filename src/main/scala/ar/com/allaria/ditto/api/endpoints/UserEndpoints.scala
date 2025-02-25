package ar.com.allaria.ditto.api.endpoints

import ar.com.allaria.ditto.api.endpoints.body.CreateUserBody
import ar.com.allaria.ditto.api.endpoints.body.CreateUserBody.createUserBody
import ar.com.allaria.ditto.api.endpoints.response.user.UserCreateResponse.userCreateResponse
import ar.com.allaria.ditto.api.endpoints.response.user._
import daruma.commons.api.Headers.Headers
import daruma.commons.api.endpoints.Endpoint
import daruma.commons.api.endpoints.Endpoint.PublicEndpoint
import daruma.commons.api.errors.ApiError
import daruma.commons.api.responses.GenericResponses._
import sttp.model.StatusCode.Ok
import sttp.tapir._

final class UserEndpoints extends Endpoint {

  override def openApiEndpoints: Seq[AnyEndpoint] = Seq(create)

  private val baseEndpoint =
    infallibleEndpoint
      .tag("Users")
      .in(headers)
      .in("users")

  def create: PublicEndpoint[(Headers, CreateUserBody), UserCreateResponse] =
    baseEndpoint.post
      .summary("Create User")
      .in(createUserBody)
      .out(userCreateResponse)
      .out(statusCode(Ok).description("Successful operation"))
      .errorOut(
        oneOf[ApiError](
          BadRequestResponse("Invalid input"),
          ForbiddenResponse,
          InternalServerErrorResponse,
          UnauthorizedResponse
        )
      )
}
