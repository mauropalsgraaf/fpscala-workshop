object Recursion {
  sealed trait List[+A]
  case object Nil extends List[Nothing]
  case class Cons[A](value: A, tail: List[A]) extends List[A]

  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def prepend[A](a: A)(list: List[A]): List[A] = Cons(a, list)

  def append[A](a: A)(list: List[A]): List[A] = list match {
    case Nil => Cons(a, Nil)
    case Cons(x, xs) => append(x)(prepend(a)(xs))
  }

  def concat[A](l1: List[A], l2: List[A]): List[A] = l1 match {
    case Nil => l2
    case Cons(x, xs) => prepend(x)(concat(xs, l2))
  }

  def map[A, B](list: List[A])(f: A => B): List[B] = list match {
    case Nil => Nil
    case Cons(x, xs) => Cons(f(x), map(xs)(f))
  }

  def filter[A](list: List[A])(f: A => Boolean): List[A] = list match {
    case Nil => Nil
    case Cons(x, xs) => if (f(x)) { prepend(x)(filter(xs)(f)) } else { filter(xs)(f) }
  }

  def any[A](list: List[A])(f: A => Boolean): Boolean = list match {
    case Nil => false
    case Cons(x, xs) => if (f(x)) true else any(xs)(f)
  }

  def all[A](list: List[A])(f: A => Boolean): Boolean = list match {
    case Nil => true
    case Cons(x, xs) => if (f(x)) all(xs)(f) else false
  }

  def flatMap[A, B](list: List[A])(f: A => List[B]): List[B] = list match {
    case Nil => Nil
    case Cons(x, xs) => concat(f(x), flatMap(xs)(f))
  }
}
