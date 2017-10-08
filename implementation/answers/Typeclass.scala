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

  implicit val listFoldable: Foldable[List] = new Foldable[List] {
    def foldLeft[A, B](fa: List[A])(z: B)(f: (B, A) => B): B = fa match {
      case Nil => z
      case Cons(x, xs) => foldLeft(xs)(f(z, x))(f)
    }
    def foldRight[A, B](fa: List[A])(z: B)(f: (A, B) => B): B = fa match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs)(z)(f))
    }
  }

  implicit val treeFoldable: Foldable[Tree] = new Foldable[Tree] {
    def foldLeft[A, B](fa: Tree[A])(z: B)(f: (B, A) => B): B = fa match {
      case Leaf(v) => f(z, v)
      case Node(l, v, r) => {
        val leftReduction = foldLeft(l)(z)(f)
        val middleReduction = f(leftReduction, v)

        foldLeft(r)(middleReduction)(f)
      }
    }
    def foldRight[A, B](fa: Tree[A])(z: B)(f: (A, B) => B): B = fa match {
      case Leaf(v) => f(z, v)
      case Node(l, v, r) => {
        val rightReduction = foldLeft(r)(z)(f)
        val middleReduction = f(rightReduction, v)

        foldLeft(l)(middleReduction)(f)
      }
    }
  }
}
