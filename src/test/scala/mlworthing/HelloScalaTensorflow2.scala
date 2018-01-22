package mlworthing

import org.platanios.tensorflow.api
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.core.client.FeedMap

object HelloScalaTensorflow2 extends App {

  //find minimum of polynomial function
  //https://www.desmos.com/calculator/49dbtqexso
  //y = 1 + 2x + 3x^2
  val initialX = 20.0f
  val x = tf.variable("x", FLOAT32, Shape(1), tf.ConstantInitializer(initialX))
//  val x = tf.placeholder(FLOAT32, Shape(1))

  val x0 = Tensor(FLOAT32, 1)
  val x1 = x
  val x2 = x * x
  val xTensor = tf.concatenate(Seq(x0, x1, x2))

  val weights = Tensor(FLOAT32, 1,2,3)

  val loss = tf.sum(xTensor *  weights) // any ideas how to improve this using tf.matmul or dot?
  val opt = tf.train.Adam(0.01).minimize(loss)


  val session = Session()
  session.run(targets = tf.globalVariablesInitializer())

//  val feeds: FeedMap = Map(
//    x -> Tensor(FLOAT32, 1)
//  )
  val feedMap = FeedMap.empty


  (0 to 100).foreach { iteration =>
    val result = session.run(feeds = feedMap, loss, opt)
    println(iteration, result.scalar)
    //    val s@Seq(resultLoss, resultX) = session.run(feeds = feedMap, Seq(loss, x), opt)
    //    println(iteration, resultX.scalar, resultLoss.scalar)
  }


}

