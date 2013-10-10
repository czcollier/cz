#!/bin/sh
exec scala -savecompiled "$0" "$@"
!#
import java.io.File

val params = args.size match {
  case 0 => (".", ".*")
  case 1 => (args(0), ".*")
  case 2 => (args(0), args(1))
}

val dir = params._1
val pattern = params._2.r

def findMatches(file: File): TraversableOnce[File] = {
  file match {
    case f if f.isDirectory => f.listFiles.flatMap(findMatches)
    case f => pattern.findFirstIn(f.getName).map(n => f)
  }
}

val matches = findMatches(new File(dir))
matches.foreach(println)
println(s"${matches.size} matches found")
