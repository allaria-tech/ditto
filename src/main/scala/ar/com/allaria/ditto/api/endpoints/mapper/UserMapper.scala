package ar.com.allaria.ditto.api.endpoints.mapper

import ar.com.allaria.ditto.api.endpoints.response.user.UserCreateResponse
import ar.com.allaria.ditto.infrastructure.database.model.User

object UserMapper {

  implicit class UserToResponse(user: User) {

    def toResponse: UserCreateResponse = UserCreateResponse(
      id = user.id,
      name = user.name,
      email = user.email
    )
  }

}
