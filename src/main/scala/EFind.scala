import com.martiansoftware.nailgun.NGContext

object EFind extends App {
  import java.io.File

  def params(args: Array[String]) = args.size match {
    case 0 => (None, ".*")
    case 1 => (Some(args(0)), ".*")
    case 2 => (Some(args(0)), args(1))
  }

  def nailMain(ctx: NGContext) {
    val (dir, patStr) = params(ctx.getArgs)
    val fullDir = dir match {
      case Some(d) => if (d.startsWith("/")) d else ctx.getWorkingDirectory + "/" + d
      case None => ctx.getWorkingDirectory 
    }

    val pattern = patStr.r
    def findMatches(file: File): TraversableOnce[File] = {
      file match {
        case f if f.isDirectory => f.listFiles.flatMap(findMatches)
        case f => pattern.findFirstIn(f.getName).map(n => f)
      }
    }

    val matches = findMatches(new File(fullDir))
    matches.foreach(println)
    println(s"${matches.size} matches found")
  }
}
