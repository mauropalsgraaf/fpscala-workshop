object Typeclass {
  sealed trait List[+A]
  case object Nil extends List[Nothing]
  case class Cons[A](v: A, tail: List[A]) extends List[A]

  sealed trait Tree[+A]
  case class Node[A](left: Tree[A], v: A, right: Tree[A]) extends Tree[A]
  case class Leaf[A](v: A) extends Tree[A]

  trait Foldable[F[_]] {
    def foldLeft[A, B](fa: F[A])(z: B)(f: (B, A) => B): B
    def foldRight[A, B](fa: F[A])(z: B)(f: (A, B) => B): B
  }
}
