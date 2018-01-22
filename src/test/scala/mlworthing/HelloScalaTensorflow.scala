package mlworthing

import org.platanios.tensorflow.api._
import org.platanios.tensorflow.examples._

object HelloScalaTensorflow extends App {

  val tensor = Tensor.zeros(INT32, Shape(2, 5))
  println(
    tensor.summarize(flattened = true)
  )

  //tensor flow examples
  LinearRegression.main(Array())
  //CIFAR.main(Array())
  //RNNTutorialUsingPTB.main(Array())
  //STL10.main(Array())
  //MNIST.main(Array())  //it barfs ....
}

