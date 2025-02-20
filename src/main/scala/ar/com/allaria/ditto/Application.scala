package ar.com.allaria.ditto

import buildinfo.BuildInfo
import cats.data.Kleisli
import cats.effect.{ExitCode, IO, IOApp}
import daruma.commons.logging.logger.Marker
import daruma.synchro.syntax.data.IOSyntax.IOLogger
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.server.middleware.CORS
import org.http4s.server.{Router, Server}
import org.http4s.{Http, Request, Response}



object Application extends IOApp with ApplicationModule {


  private implicit val mkrs: Set[Marker] = Set(Marker(s"${BuildInfo.name.toUpperCase}"))

  override def run(args: List[String]): IO[ExitCode] =
    for {
      _              <- IO.info("Environment %s", applicationConf.environment.toUpperCase)
      _              <- IO.info("Starting server...")
      code: ExitCode <- startServer
    } yield code

  val httpServer: Kleisli[IO, Request[IO], Response[IO]] =
    Router("/" -> router.routes).orNotFound

  val httpServerWithCORSPolicy: Http[IO, IO]             =
    CORS.policy.withAllowOriginAll(httpServer)

  private def startServer: IO[ExitCode] =
    BlazeServerBuilder[IO]
      .withExecutionContext(executionContext)
      .bindHttp(
        host = applicationConf.conf.server.http.host,
        port = applicationConf.conf.server.http.port
      )
      .withHttpApp(httpServerWithCORSPolicy)
      .resource
      .use(onServerStarted(_) *> IO.never)


  private def onServerStarted(server: Server): IO[Unit] =
    for {
      _ <- IO.info(
        "Server started on %s",
        s"${server.address.getHostName}:${server.address.getPort}"
      )
    } yield ()

}
