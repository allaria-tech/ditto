package ar.com.allaria.ditto

import ar.com.allaria.ditto.controller.ControllersModule
import ar.com.allaria.ditto.infrastructure.InfrastructureModule
import ar.com.allaria.ditto.modules._
import ar.com.allaria.ditto.service.ServiceModule
import buildinfo.BuildInfo
import com.softwaremill.macwire.wire
import daruma.commons.auth.authentication.entities.AuthNConfig
import daruma.commons.data.configs.environments.{EnvConfig => CommonsEnvConfig}
import daruma.commons.data.version.AppVersion
import daruma.synchro.module.SynchroModule

trait ApplicationModule
    extends AppContextModule
    with ControllersModule
    with ErrorHandlerModule
    with DatabaseModule
    with ServiceModule
    with SynchroModule
    with InfrastructureModule
    with ApiModule
    with NotificationModule
    with AuthenticationModule {

  lazy val applicationConf: ApplicationConf = wire[ApplicationConf]
  lazy val conf: ApplicationConfig          = applicationConf.conf
  lazy val env: ApplicationConfig           = applicationConf.conf

  lazy val AuthConfig: AuthNConfig = AuthNConfig(env.server.http.host)

  lazy val envConfig: CommonsEnvConfig = CommonsEnvConfig(applicationConf.environment)
  val appVersion: AppVersion           = AppVersion(BuildInfo.name, BuildInfo.version, BuildInfo.builtAtString)
  lazy val databaseConfig: MongoPropertiesConfig = env.externalServices.database

}
