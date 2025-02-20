package ar.com.allaria.ditto.modules

import ar.com.allaria.ditto.ApplicationModule
import daruma.commons.data.context.Context
import daruma.commons.logging.logger.LoggerContext

import scala.concurrent.ExecutionContext

trait AppContextModule { self: ApplicationModule =>

  implicit val executionContext: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global

  implicit val context: Context = Context(maybeUow = Some(""))

  implicit val lc: LoggerContext = LoggerContext(
    colorize = applicationConf.currentEnv.internalConfig.logger.colorize,
    emphasize = applicationConf.currentEnv.internalConfig.logger.emphasize,
    doterize = applicationConf.currentEnv.internalConfig.logger.doterize,
    newLineInLogs = applicationConf.currentEnv.internalConfig.logger.newLineInLogs
  )

}
