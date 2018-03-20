package mlworthing

import org.platanios.tensorflow.api._
import org.platanios.tensorflow.api.learn.layers.Layer
import org.platanios.tensorflow.api.ops.Math.MathOps
import org.platanios.tensorflow.api.ops.{Math, Op}
import org.platanios.tensorflow.api.tensors.TensorConvertible
import org.platanios.tensorflow.api.types.{DataType, SupportedType}
import org.tensorflow.framework.AttrValue

object DiscoverTF {

  //Tensor - describes the data
  10: Tensor
  //is the same as
  tensorConvertibleToTensor(10)(TensorConvertible.supportedTypeTensorConvertible(SupportedType.intIsSupportedType))

  //Output - exists in context of Graph and can be run in Session
  10: Output
  //is the same as
  tensorConvertibleToOutput(10)(TensorConvertible.supportedTypeTensorConvertible(SupportedType.intIsSupportedType))


  tf.constant(10)

  //evaluate on Output returns Tensor
  (10: Output).evaluate()

  //Tensor can be converted to Output
  val x0 = 10: Tensor
  x0: Output //Tensor can ba an Output
  //is the same as
  tensorConvertibleToOutput(x0)(TensorConvertible.tensorLikeTensorConvertible)

  //Output can't be converted to Tensor
  //  (10:Output):Tensor


  val placeholder: Output = tf.placeholder(FLOAT32, Shape(-1, 1))

  //Variable - is Wrapper around Output which contains variable
  val v: Variable = tf.variable(name = "x", dataType = FLOAT32, shape = Shape(1), initializer = tf.ConstantInitializer(10))

  //Represents computation unit. Takes 0-n Outputs as Inputs and produces 0-m Outputs
  type opt = Op


  // Example (see org.platanios.tensorflow.api.ops.Basic for more examples)
  //Op.Builder is private:
  //  Op.Builder(opType = "Const", name = "name")
  //    .setAttribute("value", null:AttrValue)
  //    .setAttribute("dtype", types.INT16)
  //    .build().outputs(0)

  val c = tf.matmul(null: Output, null: Output)

  type g = Graph


  // LEARN

//  learn.Input
  learn.layers.Layer
  //  tf.learn.Model(input, layer, trainInput, trainingInputLayer, loss, optimizer)


}
