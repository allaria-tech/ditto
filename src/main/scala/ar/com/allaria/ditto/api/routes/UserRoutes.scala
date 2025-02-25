package ar.com.allaria.ditto.api.routes

import ar.com.allaria.ditto.Application.serverInterpreter
import ar.com.allaria.ditto.api.endpoints.UserEndpoints
import ar.com.allaria.ditto.api.endpoints.body.CreateUserBody
import ar.com.allaria.ditto.api.endpoints.body.codecs.CreateUserBodyCodecs._
import ar.com.allaria.ditto.api.endpoints.response.user.codecs.UserCreateResponseCodec.alarmResponseEncoder
import ar.com.allaria.ditto.controller.UserController
import ar.com.allaria.ditto.controller.input.CreateUserInput
import daruma.commons.api.Headers.Headers
import daruma.commons.api.server.http4s.ServerLogic
import daruma.commons.api.server.http4s.auth.AuthNServerAuth
import daruma.commons.api.server.http4s.router.Route.Route
import daruma.commons.api.server.router.ServerRouting
import daruma.commons.data.Sentry
import daruma.commons.data.context.Context
import daruma.commons.data.handlers.ErrorHandler
import daruma.commons.logging.logger.LoggerContext
import daruma.synchro.syntax.tapir.route._

import scala.concurrent.ExecutionContext

final case class UserRoutes(serverAuth: AuthNServerAuth, endpoints: UserEndpoints, controller: UserController)(implicit
    eh: ErrorHandler,
    sentry: Sentry,
    ec: ExecutionContext,
    lc: LoggerContext
) extends ServerLogic()
    with ServerRouting[Route] {

  override def routes: Seq[Route] = Seq(create)

  def create: Route =
    serverInterpreter.toRoutes(
      endpoints.create
        .serverSecurityLogic(_ => serverAuth.authenticationNotRequired())
        .serverLogic((context: Context) =>
          (input: (Headers, CreateUserBody)) => {
            // Implicit Inputs
            implicit val headers: Headers            = input._1
            implicit val contextWithHeaders: Context = context.copy(maybeHeaders = Some(headers))

            // Initialize Sentry now because we have the headers
            sentry.initialize

            //Body
            val body: CreateUserBody = input._2

            // Controller Inputs
            val createUserInput: CreateUserInput =
              CreateUserInput(
                name = body.name,
                email = body.email
              )

            // Audit endpoint and params
            endpoints.create.auditInputWithBody(
              headers,
              body = body
            )

            validateThenGenerateLogic(
              createUserInput,
              controller.create(_: CreateUserInput),
              auditOutput = true
            )
          }
        )
    )
}
