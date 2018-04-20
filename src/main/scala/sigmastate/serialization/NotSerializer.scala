package sigmastate.serialization

import sigmastate.SType.TypeCode
import sigmastate.Values.Value
import sigmastate.serialization.ValueSerializer.Position
import sigmastate.{Not, SBoolean}
import sigmastate.serialization.OpCodes._

object NotSerializer extends ValueSerializer[Not] {

  override val opCode = NotCode
  val typeCode: TypeCode = SBoolean.typeCode

  override def parseBody(bytes: Array[Byte], pos: Position) = {
    val (body, consumed) = ValueSerializer.deserialize(bytes, pos)
    Not(body.asInstanceOf[Value[SBoolean.type]]) -> consumed
  }

  override def serializeBody(not: Not): Array[TypeCode] = ValueSerializer.serialize(not.input)
}