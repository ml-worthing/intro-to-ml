package mlworthing

import org.scalatest._
import org.scalatest.concurrent.{ Eventually, ScalaFutures }
import org.scalatest.prop.PropertyChecks

trait Spec
  extends FreeSpecLike
  with Matchers
  with DiagrammedAssertions
  with TryValues
  with EitherValues
  with OptionValues
  with AppendedClues
  with ScalaFutures
  with StreamlinedXml
  with Inside
  with Eventually
  with PropertyChecks {

  //  implicit lazy val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
}
