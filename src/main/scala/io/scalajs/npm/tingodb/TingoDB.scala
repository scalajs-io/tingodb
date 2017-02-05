package io.scalajs.npm.tingodb

import io.scalajs.RawOptions

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

/**
  * TingoDB is an embedded JavaScript in-process filesystem or in-memory database upwards compatible
  * with MongoDB at the API level.
  * @author lawrence.daniels@gmail.com
  */
@js.native
trait TingoDB extends js.Object {

    def apply(options: TingoDbOptions | RawOptions = js.native): TingoDBObject = js.native

}

/**
  * TingoDB Companion
  * @author lawrence.daniels@gmail.com
  */
@js.native
@JSImport("tingodb", JSImport.Namespace)
object TingoDB extends TingoDB