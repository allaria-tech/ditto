package ar.com.allaria.ditto.modules

import ar.com.allaria.ditto.ApplicationModule
import com.softwaremill.macwire.wire
import daruma.commons.api.server.http4s.ServerInterpreter
import daruma.commons.api.server.http4s.router.ServerRouter

trait RoutingModule { self: ApplicationModule =>

  lazy val apiRoutes = Seq()

  lazy val router: ServerRouter = wire[ServerRouter]

  lazy val serverInterpreter: ServerInterpreter = new ServerInterpreter()

}
