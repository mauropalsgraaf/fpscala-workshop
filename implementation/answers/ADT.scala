object ADT {
  sealed trait Pet
  case class Cat(name: String) extends Pet
  case class Fish(name: String, color: Color) extends Pet
  case class Squid(name: String, age: Int) extends Pet

  sealed trait Color
  case object Blue extends Color
  case object Green extends Color
  case object Orange extends Color

  def shout(pet: Pet): String = pet match {
    case Cat(name) => s"Meooowww, I'm $name"
    case Fish(name, color) => s"Blubblub, I'm a ${colorToString(color)} fishy with the name $name"
    case Squid(name, age) => s"Hello, I'm $name and i'm $age years old"
  }

  def colorToString(color: Color): String = color match {
    case Blue => "blue"
    case Green => "green"
    case Orange => "orange"
  }
}