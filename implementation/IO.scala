sealed class IO[A](io: () => A) {
  def map[B](f: A => B): IO[B] = new IO(() => f(io()))
  def flatMap[B](f: A => IO[B]): IO[B] = new IO(() => f(io()).unsafePerformIO)
  def unsafePerformIO(): A = io()
}


object IO {
  def apply[A](a: => A): IO[A] = new IO(() => a)
}

object Console {
  import IO._

  def readLine(): IO[String] = IO {
    scala.io.StdIn.readLine()
  }

  def printLine(s: String): IO[Unit] = IO {
    println(s)
  }
}
