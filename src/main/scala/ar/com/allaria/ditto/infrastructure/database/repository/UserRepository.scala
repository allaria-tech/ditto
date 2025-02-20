package ar.com.allaria.ditto.infrastructure.database.repository

import ar.com.allaria.ditto.infrastructure.database.model.User
import cats.effect.Async
import org.mongodb.scala.model.Filters
import org.mongodb.scala.result.{DeleteResult, InsertOneResult}
import org.mongodb.scala.{MongoClient, MongoCollection, MongoDatabase}

class UserRepository[F[_]: Async](mongoClient: MongoClient) {

  private val database: MongoDatabase = mongoClient.getDatabase("userdb")
  private val collection: MongoCollection[User] = database.getCollection[User]("users")

  def insertUser(user: User): F[InsertOneResult] = {
    Async[F].fromFuture(Async[F].delay(collection.insertOne(user).toFuture()))
  }

  def findUserById(id: String): F[Option[User]] = {
    Async[F].fromFuture(Async[F].delay(collection.find(Filters.equal("id", id)).first().toFutureOption()))
  }

  def deleteUser(id: String): F[DeleteResult] = {
    Async[F].fromFuture(Async[F].delay(collection.deleteOne(Filters.equal("id", id)).toFuture()))
  }
}