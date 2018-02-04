package mlworthing

import jupyter.api.Publish
import org.platanios.tensorflow.api._
import plotly.JupyterScala._
import plotly.element._

object HelloScalaTensorflow2 extends App {

  //this is provided in notebook
  implicit val publish: Publish = null

  import scala.util.Random
  implicit val session = Session()
  session.run(targets = tf.globalVariablesInitializer())

  case class TFGraph(
    x: Variable,
    x1: Output,
    loss: Output,
    opt: Op
  )

  def defineGraph(initialX: Float = 20.0f, learningRate: Double = 0.001): TFGraph = {
    val x: Variable = tf.variable(name = "x", dataType = FLOAT32, shape = Shape(1), initializer = tf.ConstantInitializer(initialX))
    val x0: Tensor = Tensor(FLOAT32, 1)
    val x1: Output = x
    val x2: Output = x * x
    val x3: Output = x * x * x
    val x4: Output = x * x * x * x
    val xTensor: Output = tf.concatenate(Seq(x0, x1, x2, x3, x4))
    //https://www.desmos.com/calculator/wrms8m8ril
    val weights: Tensor = Tensor(FLOAT32, 1, 2, 3, -4.1, 1)
    val loss: Output = tf.sum(xTensor * weights) // any ideas how to improve this using tf.matmul or dot?
    val opt: Op = tf.train.GradientDescent.apply(learningRate).minimize(loss)
    TFGraph(
      x = x,
      x1 = x1,
      loss = loss,
      opt = opt
    )
  }

  def runLoss(x: Float)(implicit session: Session, ge: TFGraph): Float = {
    val tensor: Tensor = Tensor(FLOAT32, x) //this creates Shape(1)
    session.run(targets = ge.x.assign(tensor))
    val loss: Tensor = session.run(fetches = ge.loss, targets = ge.loss)
    loss.entriesIterator.next().asInstanceOf[Float]
  }

  type PlotDivId = String

  def plotEmpty(): PlotDivId = {
    val plotDivId = "plot-" + math.abs(Random.nextInt().toLong)
    publish.html(s"""<div class="chart" id="$plotDivId"></div>""")
    plotDivId
  }

  def plotLoss(
    div: PlotDivId,
    xStart: Double = -4.0,
    xEnd: Double = 4.0
  )(implicit session: Session, ge: TFGraph): PlotDivId = {
    val howManyPoints = 100
    val xStep = (xEnd - xStart) / howManyPoints.toDouble
    val xs: Seq[Float] = (xStart to xEnd by xStep).map(_.toFloat)
    val ys: Seq[Float] = xs.map(x => runLoss(x))

    plotly.Scatter(
      xs,
      ys,
      name = "loss"
    ).plot(div = div, title = "example loss which we gonna minimize")
  }

  def optimise(iterationsNo: Int)(implicit session: Session, ge: TFGraph): Seq[(Int, Float, Float)] = (0 to iterationsNo).map { iterNo =>
    val fetches: Seq[Tensor] = session.run(fetches = Seq(ge.loss, ge.x1), targets = ge.opt)
    val loss = fetches(0).scalar.asInstanceOf[Float]
    val x = fetches(1).scalar.asInstanceOf[Float]
    (iterNo, x, loss)
  }

  def plotOptimisation(div: PlotDivId)(data: Seq[(Int, Float, Float)]): Unit = {
    def text(p: (Int, Float, Float)) =
      s"""iter:${p._1}
         |x:${p._2}
         |loss:${p._3}
       """.stripMargin

    plotly.Scatter(
      data.map(_._2),
      data.map(_._3),
      marker = Marker(size = 5, line = Line(width = 1, dash = Dash.Dot)),
      text = data.map(text),
      name = "how optimiser worked"
    ).plot(div = div)
  }

  new {
    //why new - because of problems with implicits
    //no training -> learningRate = 0.04, 3 iterations, xStart = -4.0, xEnd = 12
    implicit val tfg = defineGraph(learningRate = 0.001, initialX = 5.0f)
    val div = plotEmpty()
    plotLoss(div, xStart = -1.0, xEnd = 5)(session, tfg)
    optimise(3) |> plotOptimisation(div)
  }

}

