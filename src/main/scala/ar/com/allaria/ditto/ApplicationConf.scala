package ar.com.allaria.ditto

import daruma.commons.config.{EnvConfig => CommonsEnvConfig, Environments}
import daruma.commons.data.configs.sentry.SentryConfig
import daruma.commons.notifications.config.entities.DiscordConfig
import pureconfig.ConfigSource
import pureconfig.generic.auto._

final case class Http(host: String, port: Int)

final case class HttpServerConfig(http: Http)
final case class AwsRegion(region: String)

final case class LoggerConfig(
    colorize: Boolean,
    emphasize: Boolean,
    doterize: Boolean,
    newLineInLogs: Boolean
)

final case class NotificationsConfig(
    discord: DiscordConfig,
    sentry: SentryConfig
)

final case class InternalConfig(
    logger: LoggerConfig,
    awsRegion: AwsRegion
)

final case class EnvConfig(
    externalServices: ExternalServicesConfig,
    internalConfig: InternalConfig
) extends CommonsEnvConfig

final case class MongoPropertiesConfig(
    serverName: Option[String],
    portNumber: Option[String],
    databaseName: Option[String],
    user: Option[String],
    password: Option[String]
)

final case class ExternalServicesConfig(
    database: MongoPropertiesConfig,
    notifications: NotificationsConfig
)

final case class ApplicationConfig(
    environment: String,
    server: HttpServerConfig,
    externalServices: ExternalServicesConfig,
    internalConfig: InternalConfig
)

final case class ApplicationConf() extends Environments {
  val conf: ApplicationConfig = ConfigSource.default.loadOrThrow[ApplicationConfig]

  override def environment: String = conf.environment
}
