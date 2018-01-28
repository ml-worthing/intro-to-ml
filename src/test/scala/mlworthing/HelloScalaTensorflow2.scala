package mlworthing

import org.platanios.tensorflow.api
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.core.client.FeedMap

object HelloScalaTensorflow2 extends App {

  //find minimum of polynomial function
  //https://www.desmos.com/calculator/49dbtqexso
  //y = 1 + 2x + 3x^2

  val initialX = 20.0f
  val x: Variable = tf.variable("x", FLOAT32, Shape(1), tf.ConstantInitializer(initialX))

  val x0: Tensor = Tensor(FLOAT32, 1)
  val x1: Output = x
  val x2: Output = x * x
  val x3: Output = x * x * x
  val x4: Output = x * x * x * x

  val xTensor: Output = tf.concatenate(Seq(x0, x1, x2, x3, x4))

  val weights: Tensor = Tensor(FLOAT32, 1, 2, 3, -4.1, 1)

  val loss: Output = tf.sum(xTensor * weights) // any ideas how to improve this using tf.matmul or dot?
  val learningRate = 0.001
  val opt: Op = tf.train.GradientDescent(learningRate).minimize(loss)

  val session = Session()
  session.run(targets = tf.globalVariablesInitializer())

  val feedMap = FeedMap.empty

  (0 to 100).foreach { iteration =>
    val result: Seq[Tensor] = session.run(fetches = Seq(loss, x1), targets = opt)
    //    result.foreach(tensor => println(tensor.summarize(flattened = true)))
    println(iteration, result(0).scalar, result(1).scalar)
    //    val s@Seq(resultLoss, resultX) = session.run(feeds = feedMap, Seq(loss, x), opt)
    //    println(iteration, resultX.scalar, resultLoss.scalar)
  }

}

