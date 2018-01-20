package shapelesscirce.auto

import io.circe._
import io.circe.syntax._
import shapelesscirce._
import shapeless._
import shapeless.record._


import org.scalatest.{FunSuite, Matchers}

class AutoDecoderTests extends FunSuite with Matchers {
  test("Deserialize HNil") {
    Json.obj().as[HNil] shouldBe Right(HNil)
  }

  type Book = Record.`'author -> String, 'title -> String, 'id -> Long, 'price -> Double`.T

  test("Deserialize record type") {
    val jso = Json.obj(
      "author" := "Benjamin Pierce",
      "title" := "Types and Programming Languages",
      "id" := 262162091,
      "price" := 44.11
    )

    val result = jso.as[Book]
    result shouldBe 'right
  }
}
