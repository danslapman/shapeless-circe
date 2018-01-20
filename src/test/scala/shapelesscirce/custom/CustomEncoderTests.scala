package shapelesscirce.custom

import io.circe._
import io.circe.syntax._
import shapelesscirce._
import shapeless._
import shapeless.record._
import shapeless.syntax.singleton._

import org.scalatest.{FunSuite, Matchers}

class CustomEncoderTests extends FunSuite with Matchers {
  private type Book = Record.`'author -> String, 'title -> String, 'id -> Int, 'price -> Double`.T

  implicit val bookEncoder: Encoder[Book] =
    Encoder.forProduct4("Author", "Title", "Id", "Price")(_.values.tupled)

  test("Serialize with custom encoder") {
    val book =
      ('author ->> "Benjamin Pierce") ::
      ('title  ->> "Types and Programming Languages") ::
      ('id     ->>  262162091) ::
      ('price  ->>  44.11) ::
        HNil

    book.asJson shouldBe Json.obj(
      "Author" := "Benjamin Pierce",
      "Title" := "Types and Programming Languages",
      "Id" := 262162091,
      "Price" := 44.11
    )
  }
}
