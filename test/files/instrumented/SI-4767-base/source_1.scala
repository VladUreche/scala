package instrumented

/** Foo is a trait with a final method, that could be inlined */
trait Foo {
  @inline final def foo[T](x: T): Unit = println(x.toString)
  @inline final def foo(x: Int): Unit = println(x)
  @inline final def foo(x: Array[Int]): Unit = println(x.length)
  @inline final def foo(x: Array[String]): Unit = println(x.length)
}

class Gândacel { override def toString = "Gândacel" } // = bug (in Romanian)

/** Bar is the possible inline target */
class Bar {
  def bar(y: Foo, x1: Int, x2: Array[String], x3: Array[Int], x4: Gândacel): Unit = {
    y.foo(x1)
    y.foo(x2)
    y.foo(x3)
    y.foo[Gândacel](x4)
  }
}
