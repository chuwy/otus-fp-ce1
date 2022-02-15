package me.chuwy.otusfp.intro

import cats.effect.IO

object IOIntro {
  object Constructors {

    // 1. Чистое значение
    IO.pure(42)
    val badPure = IO.pure(println("Foo!"))
    val err = IO.fromEither(Left(new RuntimeException("Boom!")))
    val err2: IO[Int] = IO.raiseError(new RuntimeException("Boom!"))

    // 2. Сайд-эффект
    val printlnIO: IO[Unit] = IO.delay(println("Hi!"))
    val never = IO.never[Int]

    import scala.concurrent.Future

    import scala.concurrent.ExecutionContext.Implicits.global
    val fut: Future[Int] = Future.successful(3)


    val asyncInt = IO.async_ { (cb: Either[Throwable, Int] => Unit) =>
      fut.onComplete { tryResult =>
        cb(tryResult.toEither)
      }
    }


    // 3. Умные конструкторы


  }


  object Combinators {
    Constructors.asyncInt.map(_.toString)
    Constructors.asyncInt.flatMap { _ =>
      IO(println("Boom!"))
    }

    val foo: IO[Int] = Constructors.printlnIO *> Constructors.asyncInt

    def fromInt(int: Int): String = ???
  }
}
