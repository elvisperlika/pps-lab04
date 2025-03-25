package u04lab
import u03.Optionals.Optional
import u03.Sequences.*
import u03.Sequences.Sequence.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  def log[A](a: A): Unit = println("The next element is: "+a)

  def logAll[A, T[_]: Traversable](struct: T[A]): Unit =
    val traversable = summon[Traversable[T]]
    traversable.traverse(struct)(log)

  def printAll[A, T[_] : Traversable](struct: T[A]): Unit =
    val traversable = summon[Traversable[T]]
    traversable.traverse(struct)(println(_))

  trait Traversable[T[_]]:
    // def log[A](t: A): Unit = println(t)
    def traverse[A](t: T[A])(con: A => Unit): Unit

  given Traversable[Optional] with
    override def traverse[A](t: Optional[A])(con: A => Unit): Unit = t match
      case Optional.Just(elem) => con(elem)
      case _ => ()

  given Traversable[Sequence] with
    override def traverse[A](t: Sequence[A])(con: A => Unit): Unit = t match
      case Cons(h, t) => con(h); traverse(t)(con)
      case _ => ()

  @main def main() =

    val empty = Optional.Empty()
    val opt = Optional.Just("bella")
    val seq = Cons(10, Cons(20, Cons(30, Nil())))
    logAll(empty)
    printAll(empty)
    logAll(opt)
    printAll(opt)
    logAll(seq)
    println(seq)

