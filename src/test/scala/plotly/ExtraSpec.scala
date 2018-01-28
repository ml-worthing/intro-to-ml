package plotly

import io.circe.Printer
import mlworthing.Spec
import plotly.Extra.Update
import plotly.element.{ LocalDateTime, OneOrSeq }

class ExtraSpec extends Spec {

  "Plotly.extendTraces" in {

    //    val printer = Printer.noSpaces.copy(dropNullKeys = true)
    //    import io.circe.{ Error => _, _ }
    //    import io.circe.simplegeneric._
    //    import io.circe.simplegeneric.derive._
    //    import io.circe.syntax._
    //    import plotly.Codecs.Internals._
    //
    ////    import plotly._
    ////    import plotly.element._
    ////    import plotly.layout._
    //
    //
    //    printer.pretty(Seq(1,2,3).asJson) shouldBe "[1,2,3]"
    //    printer.pretty((Seq(1,2,3): Sequence).asJson) shouldBe "[1.0,2.0,3.0]"
    //    printer.pretty((Seq(1.6,2,3): Sequence).asJson) shouldBe "[1.6,2.0,3.0]"
    //    printer.pretty((Seq("one", "two"): Sequence).asJson) shouldBe """["one","two"]"""
    //
    //
    //    val s1 = Seq(1,2,3): Sequence
    //    val s2 = Seq(11,21,31): Sequence
    //    val s3 = Seq(12,22,32): Sequence
    //    val s = Some(Seq(s1, s2, s3))
    //    printer.pretty(s.asJson) shouldBe """[[1.0,2.0,3.0],[11.0,21.0,31.0],[12.0,22.0,32.0]]"""

    Extra.extendTracesJs(
      "div123",
      Update(
        x = Seq(Seq(1, 2, 3): Sequence),
        y = Seq(Seq(11, 12, 13): Sequence),
        `marker.size` = Seq(Seq(10, 20, 30): OneOrSeq[Int])
      ),
      Seq(0)
    ) shouldBe
      """(function () {
        |Plotly.extendTraces(
        |  'div123',
        |  {
        |    x: [[1.0,2.0,3.0]],
        |    y: [[11.0,12.0,13.0]],
        |    'marker.size': [[10,20,30]]
        |  },
        |  [0]
        |);
        |})();""".stripMargin withClue " all params defined"

    Extra.extendTracesJs(
      "div123",
      Update(
        y = Seq(Seq(11, 12, 13): Sequence),
        `marker.size` = Seq(Seq(10, 20, 30): OneOrSeq[Int])
      ),
      Seq(0)
    ) shouldBe
      """(function () {
        |Plotly.extendTraces(
        |  'div123',
        |  {
        |    y: [[11.0,12.0,13.0]],
        |    'marker.size': [[10,20,30]]
        |  },
        |  [0]
        |);
        |})();""".stripMargin withClue "no x params"

    Extra.extendTracesJs(
      "div123",
      Update(
        y = Seq(Seq(11, 12, 13): Sequence)
      ),
      Seq(0)
    ) shouldBe
      """(function () {
        |Plotly.extendTraces(
        |  'div123',
        |  {
        |    y: [[11.0,12.0,13.0]]
        |  },
        |  [0]
        |);
        |})();""".stripMargin withClue "no x and marker.size params"

    Extra.extendTracesJs(
      "div123",
      Update(
        x = Seq(Seq(1, 2, 3): Sequence, Seq(7, 8, 9): Sequence),
        y = Seq(Seq(11, 12, 13): Sequence, Seq(17, 18, 19): Sequence),
        `marker.size` = Seq(Seq(10, 20, 30): OneOrSeq[Int], Seq(70, 80, 90): OneOrSeq[Int])
      ),
      Seq(0, 2)
    ) shouldBe
      """(function () {
        |Plotly.extendTraces(
        |  'div123',
        |  {
        |    x: [[1.0,2.0,3.0],[7.0,8.0,9.0]],
        |    y: [[11.0,12.0,13.0],[17.0,18.0,19.0]],
        |    'marker.size': [[10,20,30],[70,80,90]]
        |  },
        |  [0,2]
        |);
        |})();""".stripMargin withClue " all params defined and two traces"

  }

}
