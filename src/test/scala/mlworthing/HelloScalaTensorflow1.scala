package mlworthing

import org.platanios.tensorflow.api._

object HelloScalaTensorflow1 extends App {

  //simple p1 + p2

  //define computation graph
  val p1: Output = tf.placeholder(FLOAT32, Shape(2), "p1")
  val p2: Output = tf.placeholder(FLOAT32, Shape(2), "p2")

  val sum = p1 + p2
  val sum2 = 2 * sum

  //create and initialize session
  val session = Session()
  session.run(targets = tf.globalVariablesInitializer()) //well, no variables in our graph but let's have it because later always it will be needed

  //provide actual values into placeholders before running the graph
  val feeds = Map(
    p1 -> Tensor(FLOAT32, 5, 6),
    p2 -> Tensor(FLOAT32, 4, 2)
  )

  //run the graph
  val result: Tensor = session.run(feeds = feeds, Seq(sum2, sum), sum2)

  //print the result of run
  println(result.shape)
  println(result.summarize())
  println(result.entriesIterator.toList.map(_.asInstanceOf[Float])) //explicit casting must be done in order to get the right type in scala

}

