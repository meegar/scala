import scala.reflect.macros.WhiteboxContext
import language.experimental.macros

object Extractor {
  def unapply(x: Int): Any = macro Macros.unapplyImpl
}

object Macros {
  def unapplyImpl(c: WhiteboxContext)(x: c.Tree) = {
    import c.universe._
    q"""
      new {
        class Match(x: Int) {
          def isEmpty = false
          def get = x
        }
        def unapply(x: Int) = new Match(x)
      }.unapply($x)
    """
  }
}
