package ar.com.allaria.ditto.service

import ar.com.allaria.ditto.ApplicationModule
import com.softwaremill.macwire.wire
import daruma.sdk.core.config.Environment
import daruma.sdk.hedwig.client.Hedwig

trait ServiceModule { self: ApplicationModule =>

  lazy val userService: UserService = wire[UserService]

  lazy val hedwig: Hedwig = Hedwig(
    Environment
      .fromDescription(
        if (conf.environment.toUpperCase == "PROD") "PROD" else "DEV"
      )
      .getOrElse(sys.error("Invalid environment"))
  )

}
