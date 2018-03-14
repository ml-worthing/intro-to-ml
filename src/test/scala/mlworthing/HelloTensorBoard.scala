package mlworthing

import java.nio.file.Paths

import org.platanios.tensorflow.api.core.client.Session
import org.platanios.tensorflow.api.io.events.SummaryFileWriter
import org.platanios.tensorflow.api.{Output, tf}

//import org.platanios.tensorflow.api._

object HelloTensorBoard extends App {


  val sess = Session()

  val (t1, t2) = tf.createWithNameScope("ns1") {
    val t1: Output = tf.constant(10, name="t1")
    val t2: Output = tf.constant(20, name="t2")
    (t1, t2)
  }

  val (t3, t4) = tf.createWithNameScope("ns2") {
    val t3: Output = t1 add t2
    val t4: Output = t1 add t3
    (t3, t4)
  }


  sess.run(targets = t4)

  SummaryFileWriter(Paths.get(s".${this.getClass.getSimpleName}"), sess.graph)
}


