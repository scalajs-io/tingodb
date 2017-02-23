package io.scalajs.npm.tingodb

import io.scalajs.RawOptions
import io.scalajs.npm.mongodb
import io.scalajs.npm.mongodb.{CollectionInfo, Cursor, DbOptions, MongoCallback}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

/**
  * TingoDB Database
  * @see [[https://mongodb.github.io/node-mongodb-native/api-articles/nodekoarticle1.html]]
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("tingodb", "Db")
class Db(databaseName: String, options: DbOptions | RawOptions = js.native) extends mongodb.Db() {

  /////////////////////////////////////////////////////////////////////////////////
  //      Methods
  /////////////////////////////////////////////////////////////////////////////////

  def collectionsInfo(): Cursor[CollectionInfo] = js.native

  def collectionNames(callback: MongoCallback[js.Array[String]]): Unit = js.native

}
