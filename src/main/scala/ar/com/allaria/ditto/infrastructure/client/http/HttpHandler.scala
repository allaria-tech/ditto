package ar.com.allaria.ditto.infrastructure.client.http

import cats.effect.IO
import daruma.commons.data.context.Context
import daruma.commons.logging.logger.LoggerContext
import daruma.commons.sttp.entities._
import daruma.synchro.syntax.data.IOSyntax.IOLogger

trait HttpHandler {

  def handleErrors[T](implicit
      c: Context,
      lg: LoggerContext
  ): HttpError => IO[T] = {
    case responseError: HttpError if responseError.code / 100 == 4 =>
      responseError.code match {
        case 400 => IO.abortAndLog(BadRequestHttpError(responseError))
        case 403 => IO.abortAndLog(ForbiddenHttpError(responseError))
        case 404 => IO.abortAndLog(NotFoundHttpError(responseError))
        case 409 => IO.abortAndLog(ConflictHttpError(responseError))
        case 422 => IO.abortAndLog(UnprocessableEntityHttpError(responseError))
        case _   =>
          for {
            _               <- IO.error("Unexpected response from client. Reason: [%s]", s"${responseError.getCause}")
            abortedResponse <- IO.abort(UnexpectedHttpError(responseError))
          } yield abortedResponse
      }

    case responseError: HttpError =>
      for {
        _               <- IO.error("Unexpected response from client. Reason: [%s]", s"${responseError}")
        abortedResponse <- IO.abort(UnexpectedHttpError(responseError))
      } yield abortedResponse
  }

  def handleResponse[T](implicit
      c: Context,
      lg: LoggerContext
  ): HttpResponse[T] => IO[T] = response =>
    for {
      _ <- IO.info(s"Body from response: ${response.body.toString}")
    } yield response.body

}
