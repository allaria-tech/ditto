package ar.com.allaria.ditto.infrastructure.database.repository

import ar.com.allaria.ditto.infrastructure.database.model.User
import ar.com.allaria.ditto.service.input.InsertUserRepositoryInput
import cats.effect.Async
import cats.effect.std.UUIDGen
import daruma.commons.data.context.Context
import daruma.commons.logging.logger.LoggerContext
import org.mongodb.scala.model.Filters
import org.mongodb.scala.result.DeleteResult
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

class UsersRepository[F[_]: Async](mongoClient: MongoClient) {

  private val database: MongoDatabase = mongoClient.getDatabase("usersDb")
  private val collection: MongoCollection[User] = database.getCollection[User]("users")

  def insertUser(userInput: InsertUserRepositoryInput)(implicit c: Context, lc: LoggerContext): F[User] = {
    val user = User(id=UUIDGen.randomString.toString, name=userInput.name, email=userInput.email)
    Async[F].fromFuture(Async[F].delay(collection.insertOne(user).toFuture()))
    Async[F].delay(user)
  }

  def findUserById(id: String): F[Option[User]] = {
    Async[F].fromFuture(Async[F].delay(collection.find(Filters.equal("id", id)).first().toFutureOption()))
  }

  def deleteUser(id: String): F[DeleteResult] = {
    Async[F].fromFuture(Async[F].delay(collection.deleteOne(Filters.equal("id", id)).toFuture()))
  }
}