
import io.circe.generic.auto._
import io.circe.syntax._
import jupyter.api.Publish
import org.scalatest._
import org.scalatest.concurrent.{ Eventually, ScalaFutures }

/**
 * Companion object which brings stuff when working in jupyter notebook.
 */
package object mlworthing
  extends Matchers
  with DiagrammedAssertions
  with TryValues
  with EitherValues
  with OptionValues
  with AppendedClues
  with ScalaFutures
  with StreamlinedXml
  with Inside
  with Eventually { self =>

  val test = "mlworthing package object 2"

  private[mlworthing] implicit class PipeOps[A](val a: A) extends AnyVal {
    def |>[B](f: A => B) = f(a)
  }

  implicit class ArrayArrayOps[A](val xs: Array[Array[A]]) extends AnyRef {

    def showTable[B](headers: String*)(implicit publish: jupyter.api.Publish) = {
      //format: OFF
      <table>
        <tr>
          {headers.map(h => <th>
          {h}
        </th>)}
        </tr>{xs.map(a =>
        <tr>
          {a.map(x => <td>
          {x}
        </td>)}
        </tr>)}
      </table>
      //format: ON
    }.toString |> publish.html
  }

  implicit class ArrayOps[A](val xs: Array[A]) extends AnyRef {
    def showTable[B](header: String)(implicit publish: jupyter.api.Publish) = {
      //format: OFF
      <table>
        <tr>
          <th>
            {header}
          </th>
        </tr>
        {xs.map(a => <tr><td>{a}</td></tr>)}
      </table>
      //format: ON
    }.toString |> publish.html
  }

}
