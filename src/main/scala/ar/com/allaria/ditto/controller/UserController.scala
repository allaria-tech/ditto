package ar.com.allaria.ditto.controller

import ar.com.allaria.ditto.api.endpoints.mapper.UserMapper.UserToResponse
import ar.com.allaria.ditto.api.endpoints.response.user.UserCreateResponse
import ar.com.allaria.ditto.controller.input.CreateUserInput
import ar.com.allaria.ditto.infrastructure.database.model.User
import ar.com.allaria.ditto.service.UserService
import cats.effect.IO
import daruma.commons.data.context.Context
import daruma.commons.logging.logger.{LoggerContext, Marker}
import daruma.synchro.syntax.data.IOSyntax.IOLogger

final class UserController(
    usersService: UserService
)(implicit mkrs: Set[Marker] = Set(Marker(s"CONTROLLER"), Marker(s"USERS"))) {

  def create(input: CreateUserInput)(implicit c: Context, lc: LoggerContext): IO[UserCreateResponse] =
    for {
      _          <- IO.info("Creating alarm")
      user: User <- usersService.create(input)
      _          <- IO.info("Alarm %s created", s"${user.id}")
    } yield user.toResponse

}
