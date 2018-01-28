package plotly

import io.circe.Printer
import io.circe.syntax._
import jupyter.api.Publish
import plotly.element.OneOrSeq

object Extra {

  case class Update(
    x: Option[Seq[Sequence]],
    y: Option[Seq[Sequence]],
    `marker.size`: Option[Seq[OneOrSeq[Int]]]
  // other attributes ...
  )

  object Update {
    def apply(
      x: Seq[Sequence] = null,
      y: Seq[Sequence] = null,
      `marker.size`: Seq[OneOrSeq[Int]] = null
    // other attributes ...
    ): Update =
      Update(
        Option(x),
        Option(y),
        Option(`marker.size`)
      )
  }

  def waitForChart(timeoutMs: Long = 500): Unit = Thread.sleep(timeoutMs)

  def extendTraces(div: String, u: Update, indices: Seq[Int])(implicit publish: Publish): Unit = {
    val baseJs = extendTracesJs(div, u, indices)

    val js =
      s"""requirejs(["plotly"], function(Plotly) {
         |  $baseJs
         |});
      """.stripMargin

    publish.js(js)
  }

  def extendTraces(div: String, xs: Sequence, ys: Sequence)(implicit publish: Publish): Unit = {
    val u = Update(
      Seq(xs), Seq(ys)
    )
    val js = extendTracesJs(div, u, Seq(0))
    publish.js(js)
  }

  def extendTracesJs(div: String, e: Update, indices: Seq[Int]): String = {
    import io.circe.{ Error => _, _ }
    import io.circe.simplegeneric._
    import io.circe.simplegeneric.derive._
    import io.circe.syntax._
    import plotly.Codecs.Internals._

    val xJs = e.x.map(i => "x: " + printer.pretty(i.asJson))
    val yJs = e.y.map(i => "y: " + printer.pretty(i.asJson))
    val markerSizeJs = e.`marker.size`.map(i => "'marker.size': " + printer.pretty(i.asJson) + "")

    val params = List(xJs, yJs, markerSizeJs).collect {
      case Some(x) => x
    }.mkString(",\n    ")

    val indicesJs = printer.pretty(indices.asJson)

    val js =
      s"""Plotly.extendTraces(
         |  '$div',
         |  {
         |    $params
         |  },
         |  $indicesJs
         |);""".stripMargin

    s"""(function () {
           |$js
           |})();""".stripMargin
  }

  private lazy val printer = Printer.noSpaces.copy(dropNullKeys = true)

}
