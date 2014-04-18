import com.martiansoftware.nailgun.NGContext
import scala.io.Source

/**
 * Created by ccollier on 4/17/14.
 */
object Sed {
  def nailMain(context: NGContext) {
    val wordArg = context.getArgs.headOption
    val dist = context.getArgs.tail.headOption
    val dict = Source.fromFile("/usr/share/dict/words").getLines

    for (dictWord <- dict; word <- wordArg; dStr <-dist) {
      val d = dStr.toInt
      val dist = levenshtein1(word, dictWord)
      if (dist <= d)
        println(dictWord)
    }
  }

  private def minimum(i1: Int, i2: Int, i3: Int) = Math.min(Math.min(i1, i2), i3)
  def levenshtein1(s1:String, s2:String) = {
    val dist = Array.tabulate(s2.length + 1, s1.length + 1) {
      (j, i) => if (j == 0) i else if (i == 0) j else 0
    }

    for(j <- 1 to s2.length; i <- 1 to s1.length)
      dist(j)(i) =
        if (s2(j - 1) == s1(i - 1)) dist(j - 1)(i - 1)
        else minimum(dist(j - 1)(i), dist(j)(i - 1), dist(j - 1)(i - 1)) + 1

    dist(s2.length)(s1.length)
  }
}

