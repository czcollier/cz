import com.martiansoftware.nailgun.NGContext
 
object Rev extends App {
  case class Result(payload: String) {
    val reversed = payload.reverse
    override def toString = reversed
  }

  def nailMain(context: NGContext) {
    val r = Result(context.getArgs mkString " ")
    println(context.getWorkingDirectory)
    println(r)
  }
}
