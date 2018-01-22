package mlworthing

import plotly.element.Orientation.Vertical

/**
 * Dummy object for exploring plotty api with intellij support
 */
private object PlottyApiExplorer {

  import plotly._
  import plotly.element._

  //  import plotly.JupyterScala._

  //  plotly.JupyterScala.init()

  val (x: Seq[String], y: Seq[Int]) = Seq(
    "Banana" -> 10,
    "Apple" -> 8,
    "Grapefruit" -> 5
  ).unzip

  Bar(x, y, orientation = Vertical)
  //    .plot()

  new {
    val x = (0.0 to 10.0 by 0.1)
    val y1 = x.map(d => 2.0 * d + util.Random.nextGaussian())
    val y2 = x.map(math.exp)

    val plot = Seq(
      Scatter(
        x, y1,
        name = "Approx twice",
        mode = ScatterMode(ScatterMode.Lines, ScatterMode.Markers)
      ),
      Scatter(
        x, y2, name = "Exp"
      )
    )
    //    plot.plot()
  }

}
