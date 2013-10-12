object Rev extends App {
  case class Result(payload: String) {
    val reversed = payload.reverse
    override def toString = reversed
  }

  val r = Result(args(0))
  println(r)
}
