package ar.com.allaria.ditto.controller.input

import daruma.commons.data.validation.DarumaValidation._
import daruma.commons.data.validation.Validable
import daruma.synchro.syntax.validations.validationImplicits.ValidatedTupleCombine2

final case class CreateUserInput(
    name: String,
    email: String
) extends Validable {

  override def validate: DarumaValidation = (
    validateSimpleField("email", email),
    validateSimpleField("name", name)
  ).combine
}
