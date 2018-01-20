package shapelesscirce.auto

import io.circe._
import io.circe.syntax._
import shapelesscirce._
import shapeless._
import shapeless.syntax.singleton._
import org.scalatest.{FunSuite, Matchers}

class AutoEncoderTests extends FunSuite with Matchers {
  test("Serialize HNil") {
    (HNil: HNil).asJson shouldBe Json.obj()
  }

  test("Serialize record instance") {
    val book =
      ('author ->> "Benjamin Pierce") ::
      ('title  ->> "Types and Programming Languages") ::
      ('id     ->>  262162091) ::
      ('price  ->>  44.11) ::
        HNil

    book.asJson shouldBe Json.obj(
      "author" := "Benjamin Pierce",
      "title" := "Types and Programming Languages",
      "id" := 262162091,
      "price" := 44.11
    )
  }
}
