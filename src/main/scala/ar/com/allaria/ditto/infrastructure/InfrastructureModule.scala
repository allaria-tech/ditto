package ar.com.allaria.ditto.infrastructure

import ar.com.allaria.ditto.ApplicationModule
import ar.com.allaria.ditto.infrastructure.database.repository.UsersRepository
import ar.com.allaria.ditto.modules.DatabaseModule
import cats.effect.IO
import com.softwaremill.macwire.wire

trait InfrastructureModule extends DatabaseModule { self: ApplicationModule =>

  //Repositories
  lazy val userRepository: UsersRepository[IO] = wire[UsersRepository[IO]]

}
