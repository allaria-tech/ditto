package ar.com.allaria.ditto.service.input.mapper

import ar.com.allaria.ditto.controller.input.CreateUserInput
import ar.com.allaria.ditto.service.input.InsertUserRepositoryInput

object UserInputMapper {

  implicit class CrateUserInputControllerToServiceMapper(
      input: CreateUserInput
  ) {

    def toServiceInput: InsertUserRepositoryInput =
      InsertUserRepositoryInput(
        name = input.name,
        email = input.email
      )
  }
}
