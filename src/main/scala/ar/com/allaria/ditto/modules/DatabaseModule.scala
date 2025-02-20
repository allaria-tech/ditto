package ar.com.allaria.ditto.modules

import ar.com.allaria.ditto.ApplicationModule
import org.mongodb.scala.MongoClient

trait DatabaseModule { self: ApplicationModule =>

  implicit lazy val darumaDb: MongoClient = MongoClient("mongodb://root:example@localhost:27017")

}
