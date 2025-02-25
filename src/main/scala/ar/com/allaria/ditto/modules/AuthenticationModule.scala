package ar.com.allaria.ditto.modules

import ar.com.allaria.ditto.ApplicationModule
import com.softwaremill.macwire.wire
import daruma.commons.api.server.http4s.auth.AuthNServerAuth
import daruma.commons.auth.authentication.AuthNAuthenticator
import daruma.commons.auth.authentication.clients.AuthNClient
import daruma.commons.auth.authorization.Authorizer
import daruma.commons.sttp.application.HttpClient

trait AuthenticationModule { self: ApplicationModule =>

  lazy val authNHttpClient: HttpClient                = wire[HttpClient]
  lazy val authNClient: AuthNClient                   = wire[AuthNClient]
  lazy implicit val authenticator: AuthNAuthenticator = wire[AuthNAuthenticator]
  lazy val serverAuth: AuthNServerAuth                = wire[AuthNServerAuth]
  lazy implicit val authorizer: Authorizer            = wire[Authorizer]

}
