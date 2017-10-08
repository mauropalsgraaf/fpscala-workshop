object Recursion {
  sealed trait List[+A]
  case object Nil extends List[Nothing]
  case class Cons[A](value: A, tail: List[A]) extends List[A]

  def sum(list: List[Int]): Int = ???

  def append[A](a: A)(list: List[A]): List[A] = ???

  def prepend[A](a: A)(list: List[A]): List[A] = ???

  def concat[A](l1: List[A], l2: List[A]): List[A] = ???

  def map[A, B](list: List[A])(f: A => B): List[B] = ???
  
  def filter[A](list: List[A])(f: A => Boolean): List[A] = ???

  def any[A](list: List[A])(f: A => Boolean): Boolean = ???

  def all[A](list: List[A])(f: A => Boolean): Boolean = ???

  def flatMap[A, B](list: List[A])(f: A => List[B]): List[B] = ???
}
