package ar.com.allaria.ditto.service

import ar.com.allaria.ditto.controller.input.CreateUserInput
import ar.com.allaria.ditto.infrastructure.database.model.User
import ar.com.allaria.ditto.infrastructure.database.repository.UsersRepository
import ar.com.allaria.ditto.service.input.mapper.UserInputMapper.CrateUserInputControllerToServiceMapper
import cats.effect.IO
import daruma.commons.data.context.Context
import daruma.commons.logging.logger.{LoggerContext, Marker}
import daruma.synchro.syntax.data.IOSyntax.IOLogger

final class UserService(usersRepository: UsersRepository[IO])(implicit
                                                              mkrs: Set[Marker] = Set(Marker("USERS SERVICE"))
) {


  def create(input: CreateUserInput)(implicit c: Context, lc: LoggerContext): IO[User] = {
    for {
      _                         <- IO.info("Creating User")
      user: User        <- usersRepository.insertUser(input.toServiceInput)
      _                         <- IO.info("Alarm created")
    } yield user
  }

}
