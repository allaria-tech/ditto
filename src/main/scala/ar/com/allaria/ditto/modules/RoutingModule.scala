package ar.com.allaria.ditto.modules

import ar.com.allaria.ditto.ApplicationModule
import ar.com.allaria.ditto.api.routes.UserRoutes
import com.softwaremill.macwire.wire
import daruma.commons.api.server.http4s.ServerInterpreter
import daruma.commons.api.server.http4s.router.Route.Route
import daruma.commons.api.server.http4s.router.ServerRouter
import daruma.commons.api.server.router.ServerRouting

trait RoutingModule { self: ApplicationModule =>

  lazy val userRoutes: UserRoutes = wire[UserRoutes]

  lazy val apiRoutes: Seq[ServerRouting[Route]] = Seq[ServerRouting[Route]](userRoutes)

  lazy val router: ServerRouter = wire[ServerRouter]

  lazy val serverInterpreter: ServerInterpreter = new ServerInterpreter()

}
