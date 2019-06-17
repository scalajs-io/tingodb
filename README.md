TingoDB API for Scala.js
=======================
[tingodb](https://www.npmjs.com/package/tingodb) - Embedded Node.js database upward compatible with MongoDB.

#### Description

TingoDB is an embedded JavaScript in-process filesystem or in-memory database upwards compatible with MongoDB at the API level.

Upwards compatible means that if you build an app that uses functionality implemented by TingoDB 
you can switch to MongoDB almost without code changes. This greatly reduces implementation risks 
and give you freedom to switch to a mature solution at any moment.

As a proof for upward compatibility, all tests designed to run against both MongoDB and TingoDB. 
Moreover, significant parts of tests contributed from MongoDB nodejs driver projects and are used 
as is without modifications.

For those folks who familiar with the Mongoose.js ODM, we suggest to look at Tungus, an experimental 
driver that allows using the famous ODM tool with our database.

TingoDB can be dropin replacement for existing apps and frameworks that are based on MongoDB.

### Build Dependencies


* [SBT v1.2.x](http://www.scala-sbt.org/download.html)

### Build/publish the SDK locally

```bash
$ sbt clean publish-local
```

### Running the tests

Before running the tests the first time, you must ensure the npm packages are installed:

```bash
$ npm install
```

Then you can run the tests:

```bash
$ sbt test
```

### Examples

```scala
import io.scalajs.JSON
import io.scalajs.nodejs.console
import io.scalajs.nodejs.fs.Fs
import io.scalajs.npm.mongodb.{MongoError, doc}
import io.scalajs.npm.tingodb._
import scala.scalajs.js

  
val dbPath = "./src/test/resources/"
val collName = "actresses"
val collPath = dbPath + "/" + collName  
  
// clear the test
Fs.writeFileSync(collPath, "")

// open a connection
val tingoDB = TingoDB(new TingoDbOptions())
val db = tingoDB.Db.apply(dbPath, new TingoDbOptions())
val collection = db.collection(collName)

// define some actors
val actors = js.Array(
    new Actor(firstName = "Drew", lastName = "Barrymore", age = 41),
    new Actor(firstName = "Halle", lastName = "Berry", age = 50),
    new Actor(firstName = "Grace", lastName = "Park", age = 42)
)

// insert the actors into the database
collection.insert(actors, (err, result) => {
    if (err != null) console.log("err =>", err)
    else {
      console.log(s"result => ${JSON.stringify(result)}")
    
      // read the data from the database
      collection.findOne(doc("firstName" -> "Grace"), (err: MongoError, actor: Actor) => {
        if (err != null) console.log("err =>", err)
        else {
          console.log(s"actor => ${JSON.stringify(actor)}")
        }
      })
    }
})


class Actor(var firstName: js.UndefOr[String] = js.undefined,
            var lastName: js.UndefOr[String] = js.undefined,
            var age: js.UndefOr[Int] = js.undefined) 
  extends js.Object
```

### Artifacts and Resolvers

To add the `tingodb` binding to your project, add the following to your build.sbt:  

```sbt
libraryDependencies += "io.scalajs.npm" %%% "tingodb" % "0.5.0"
```

Optionally, you may add the Sonatype Repository resolver:

```sbt   
resolvers += Resolver.sonatypeRepo("releases") 
```