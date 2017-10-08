object TailRecursion {
  sealed trait List[+A]
  case object Nil extends List[Nothing]
  case class Cons[A](value: A, tail: List[A]) extends List[A]

  def sum(list: List[Int]): Int = {
    @annotation.tailrec
    def loop(list: List[Int], acc: Int): Int = list match {
      case Nil => acc
      case Cons(x, xs) => loop(xs, acc + x)
    }

    loop(list, 0)
  }

  // Prepending an item is not a recursive call
  def prepend[A](a: A)(list: List[A]): List[A] = Cons(a, list)

  // To be implemented
  def append[A](a: A)(list: List[A]): List[A] = {
    @annotation.tailrec
    def loop(a: A)(list: List[A], newList: List[A]): List[A] = list match {
      case Nil => reverse(newList)
      case Cons(x, xs) => loop(a)(xs, prepend(x)(newList))
    }

    loop(a)(list, Nil)
  }

  def concat[A](l1: List[A], l2: List[A]): List[A] = {
    @annotation.tailrec
    def loop(list1: List[A], list2: List[A]): List[A] = list1 match {
      case Nil => list2
      case Cons(x, xs) => loop(xs, append(x)(list2))
    }

    loop(reverse(l1), l2)
  }

  def map[A, B](list: List[A])(f: A => B): List[B] = {
    @annotation.tailrec
    def loop(list: List[A], newList: List[B]): List[B] = list match {
      case Nil => reverse(newList)
      case Cons(x, xs) => loop(xs, append(f(x))(newList))
    }

    loop(reverse(list), Nil)
  }

  def filter[A](list: List[A])(f: A => Boolean): List[A] = {
    @annotation.tailrec
    def loop(list: List[A], filteredList: List[A]): List[A] = list match {
      case Nil => reverse(filteredList)
      case Cons(x, xs) if (f(x)) => loop(xs, prepend(x)(filteredList))
      case Cons(x, xs) => loop(xs, filteredList)
    }

    loop(reverse(list), Nil)
  }

  def any[A](list: List[A])(f: A => Boolean): Boolean = list match {
    case Nil => false
    case Cons(x, xs) if (f(x)) => true
    case Cons(x, xs) => any(xs)(f)
  }

  def all[A](list: List[A])(f: A => Boolean): Boolean = list match {
    case Nil => true
    case Cons(x, xs) if (!f(x)) => false
    case Cons(x, xs) => all(xs)(f)
  }

  def flatMap[A, B](list: List[A])(f: A => List[B]): List[B] = {
    @annotation.tailrec
    def loop(list: List[A], newList: List[B])(f: A => List[B]): List[B] = list match {
      case Nil => newList
      case Cons(x, xs) => loop(xs, concat(newList, f(x)))(f)
    }

    loop(list, Nil)(f)
  }

  def reverse[A](list: List[A]): List[A] = {
    @annotation.tailrec
    def loop(list: List[A], newList: List[A]): List[A] = list match {
      case Nil => newList
      case Cons(x, xs) => {
        loop(xs, append(x)(newList))
      }
    }

    loop(list, Nil)
  }
}
