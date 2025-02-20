package ar.com.allaria.ditto.modules

import ar.com.allaria.ditto.ApplicationModule
import com.softwaremill.macwire.wire
import daruma.commons.data.Sentry
import daruma.commons.data.handlers.ErrorHandler

trait ErrorHandlerModule { self: ApplicationModule =>

  lazy implicit val sentry: Sentry             = new Sentry(
      env.externalServices.notifications.sentry,
      discord
    )
  lazy implicit val errorHandler: ErrorHandler = wire[ErrorHandler]

}
