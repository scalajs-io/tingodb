TingoDB API for Scala.js
=======================
This is a Scala.js type-safe binding for [tingodb](https://www.npmjs.com/package/tingodb) 

Embedded Node.js database upward compatible with MongoDB.

#### Build Dependencies

* [ScalaJs.io v0.3.x](https://github.com/ldaniels528/scalajs.io)
* [SBT v0.13.13](http://www.scala-sbt.org/download.html)

#### Build/publish the SDK locally

```bash
$ sbt clean publish-local
```

#### Running the tests

Before running the tests the first time, you must ensure the npm packages are installed:

```bash
$ npm install
```

Then you can run the tests:

```bash
$ sbt test
```

#### Examples

```scala
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
      info(s"result => ${JSON.stringify(result)}")
    
      // read the data from the database
      collection.findOne(doc("firstName" -> "Grace"), (err: MongoError, actor: Actor) => {
        if (err != null) console.log("err =>", err)
        else {
          info(s"actor => ${JSON.stringify(actor)}")
        }
      })
    }
})
```

```scala
@ScalaJSDefined
class Actor(var firstName: js.UndefOr[String] = js.undefined,
          var lastName: js.UndefOr[String] = js.undefined,
          var age: js.UndefOr[Int] = js.undefined) extends js.Object
```

#### Artifacts and Resolvers

To add the `tingodb` binding to your project, add the following to your build.sbt:  

```sbt
libraryDependencies += "io.scalajs.npm" %%% "tingodb" % "0.3.0.3"
```

Optionally, you may add the Sonatype Repository resolver:

```sbt   
resolvers += Resolver.sonatypeRepo("releases") 
```