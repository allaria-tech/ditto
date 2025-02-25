package ar.com.allaria.ditto.controller

import ar.com.allaria.ditto.ApplicationModule
import ar.com.allaria.ditto.modules.ApiModule
import com.softwaremill.macwire.wire

trait ControllersModule extends ApiModule { self: ApplicationModule =>

  lazy val userController: UserController = wire[UserController]
}
