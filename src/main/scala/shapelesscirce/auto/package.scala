package shapelesscirce

import cats.syntax.either._
import io.circe._
import shapeless._
import shapeless.labelled._

package object auto {
  implicit def recordEncoder[K <: Symbol, H, T <: HList](implicit
    witness: Witness.Aux[K],
    hEncoder: Lazy[Encoder[H]],
    tEncoder: ObjectEncoder[T]
  ): ObjectEncoder[FieldType[K, H] :: T] = {
    (hl: FieldType[K, H] :: T) => {
      (witness.value.name -> hEncoder.value(hl.head)) +: tEncoder.encodeObject(hl.tail)
    }
  }

  implicit def recordDecoder[K <: Symbol, H, T <: HList](implicit
    witness: Witness.Aux[K],
    hDecoder: Lazy[Decoder[H]],
    tDecoder: Decoder[T]
  ): Decoder[FieldType[K, H] :: T] = (json: HCursor) => {
    val fieldName = witness.value.name

    json.value match {
      case JSObject(jso) =>
        jso(fieldName) match {
          case Some(jsv) =>
            for {
              hv <- hDecoder.value.decodeJson(jsv)
              tv <- tDecoder.decodeJson(json.value)
            } yield {
              field[K](hv) :: tv
            }
          case None =>
            Left(DecodingFailure(s"Field '$fieldName' not found", Nil))
        }
      case _ =>
        Left(DecodingFailure(s"${json.getClass} can't be deserialized into record type", Nil))
    }
  }
}
