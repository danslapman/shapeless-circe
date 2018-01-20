import io.circe._
import shapeless._

package object shapelesscirce {
  implicit val hNilEncoder: ObjectEncoder[HNil] =
    (_: HNil) => JsonObject()

  implicit val hNilDecoder: Decoder[HNil] =
    Decoder.decodeJsonObject.map(_ => HNil)

  private[shapelesscirce] object JSObject {
    def unapply(arg: Json): Option[JsonObject] = arg.asObject
  }
}
