package mlworthing

import jupyter.api.Publish
import plotly.element.Orientation.Vertical
import plotly.layout.Layout

import scala.collection.immutable

/**
  * Dummy object for exploring plotty api with intellij support
  */
private object PlottyApiExplorer {

  import plotly._
  import plotly.element._
  import plotly.JupyterScala._

  //  plotly.JupyterScala.init()

  val (x: Seq[String], y: Seq[Int]) = Seq(
    "Banana" -> 10,
    "Apple" -> 8,
    "Grapefruit" -> 5
  ).unzip

  Bar(x, y, orientation = Vertical)
  //      .plot()
  new {
    val x = (0.0 to 10.0 by 0.1)
    val y1: immutable.IndexedSeq[Double] = x.map(d => 2.0 * d + util.Random.nextGaussian())
    val y2: immutable.IndexedSeq[Double] = x.map(math.exp)

    val plot: Seq[Trace] = Seq(
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

  implicit val publish: Publish = ???

  //  new {
  //
  //    val x = List(1, 2, 3)
  //    val y = List(10, 11, 12)
  //
  //    val trace: Trace = Scatter(
  //      x,
  //      y,
  //      name = "name123"
  //    )
  //
  //    Plotly.jsSnippet("div", Seq(trace), Layout())
  //
  //    val div = Scatter().plot()
  //    val div = "div123"
  //
  //    (0 to 100).foreach { iter =>
  //      Thread.sleep(400)
  //      val x = iter * 0.01
  //      val y = Math.cos(x)
  //
  //      val x = 10
  //      val y = 11
  //
  //      val update = Update(
  //        xs = Seq(List(x)),
  //        ys = Seq(List(y))
  //      )
  //
  //      extendTracesJs(div, update, List(0))
  //
  //      extendTraces(div, update, List(0))(publish)
  //    }
  //  }

  //
  //  new {
  //    val div = "plot-div-123"
  //    publish.html(s"""<div class="chart" id="$div"></div>""")
  //    val jsCode = s"""
  //      TESTER = document.getElementById('$div');
  //      Plotly.plot( TESTER, [{
  //        x: [1, 2, 3, 4, 5],
  //        y: [1, 2, 4, 8, 16]
  //      }],
  //      {
  //      margin: { t: 0 } } );
  //    """
  //      publish.javascript(jsCode)
  //  }
  //
  //
  new {

    import mlworthing._
    import plotly.Extra._

    import mlworthing._
    import plotly.Extra._
    import scala.util.Random._

    val div = Scatter(
      List[Double](),
      List[Double](),
      marker = Marker(
        size = Seq()
      )
    ).plot()

    //       waitForChart()
    (0 to 30) foreach { i =>
      Thread.sleep(100)
      val update = Update(
        x = Seq[Sequence](List(nextGaussian())),
        y = Seq[Sequence](List(nextGaussian())),
        `marker.size` = Seq[OneOrSeq[Int]](List(nextInt().abs % 100))
      )
      extendTraces(div, update, List(0))
    }
  }
}
