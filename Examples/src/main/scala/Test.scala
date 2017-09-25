package scala


object Test {

  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case x :: xs => x + sum(xs)
  }

  def main(args: Array[String]): Unit = {
    println(sum(1 until 10000 toList))
  }
}
