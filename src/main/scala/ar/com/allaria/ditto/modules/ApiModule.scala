package ar.com.allaria.ditto.modules

import ar.com.allaria.ditto.ApplicationModule
import ar.com.allaria.ditto.api.endpoints.UserEndpoints
import com.softwaremill.macwire.wire
import daruma.commons.api.endpoints.Endpoint
import sttp.apispec.openapi.Server

trait ApiModule extends OpenApiModule with RoutingModule { self: ApplicationModule =>

  private lazy val ip: String             = applicationConf.conf.server.http.host
  lazy val port: Int                      = applicationConf.conf.server.http.port

  lazy val userEndpoints: UserEndpoints   = wire[UserEndpoints]

  lazy val apiServers: Seq[Server] =
    Seq(
      if (ip != "0.0.0.0")
        Server(
          url = s"http://$ip:$port",
          description = Some("Local server")
        )
      else {
        Server(
          url = "https://ditto.svc.internal.allaria.dev",
          description = Some("Dev server")
        )
      }
    )

  lazy val apiEndpoints: Seq[Endpoint] =
    Seq[Endpoint](userEndpoints)

}
