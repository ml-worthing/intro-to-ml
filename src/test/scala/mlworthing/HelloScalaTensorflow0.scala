package mlworthing

import org.platanios.tensorflow.api
import org.platanios.tensorflow.api._
import org.platanios.tensorflow.examples._

object HelloScalaTensorflow0 extends App {

//  tensor flow examples
  LinearRegression.main(Array())
//  CIFAR.main(Array())
//  RNNTutorialUsingPTB.main(Array())
//  STL10.main(Array())
  MNIST.main(Array())  //it barfs ....
}

