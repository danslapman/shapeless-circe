shapeless-circe [ ![Download](https://api.bintray.com/packages/danslapman/maven/shapeless-circe/images/download.svg) ](https://bintray.com/danslapman/maven/shapeless-circe/_latestVersion)
===================================================================================================================================================================================================

**shapeless-circe** provides JSON (un)marshalling support for [shapeless](https://github.com/milessabin/shapeless)'s extensible records via circe.
Here is a brief usage example:
```scala
import io.circe._
import io.circe.parser._
import io.circe.syntax._
import shapeless._
import shapeless.record._
import shapeless.syntax.singleton._
import shapelesscirce._
import shapelesscirce.auto._

val book =
  ('author ->> "Benjamin Pierce") ::
    ('title  ->> "Types and Programming Languages") ::
    ('id     ->>  262162091) ::
    ('price  ->>  44.11) ::
    HNil
val book2 = book + ('tag ->> "programming")
val bookJson = book2.asJson
val bookJsonStr = bookJson.noSpaces
println(bookJsonStr)

type Book = Record.`'author -> String, 'title -> String, 'id -> Int, 'price -> Double`.T
val deserializedBook = parse(bookJsonStr).toOption.get.as[Book]
println(deserializedBook)
```

shapeless-circe is available via bintray:
```
    resolvers += Resolver.bintrayRepo("danslapman", "maven")

    libraryDependencies += "danslapman" %% "shapeless-circe" % "{version}"
```