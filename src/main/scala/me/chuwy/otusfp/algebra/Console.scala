package me.chuwy.otusfp.algebra

import scala.io.StdIn

import cats.data.State
import cats.Monad
import cats.implicits._

import cats.effect.IO

trait Console[F[_]] {
  def putStrLn(str: String): F[Unit]
  def readLine: F[String]
}

object Console {

  case class RealWorld(log: List[String]) {
    def getResult = log.reverse
  }

  type Test[A] = State[RealWorld, A]

  // Summoner
  def apply[F[_]](implicit ev: Console[F]): Console[F] = ev

  implicit val interpreter: Console[IO] =
    new Console[IO] {
      def putStrLn(str: String): IO[Unit] =
        IO.delay(println(str))

      def readLine: IO[String] =
        IO.delay(StdIn.readLine())
    }

  implicit def testInterpreter: Console[Test] =
    new Console[Test] {
      def putStrLn(str: String): Test[Unit] = {
        val stateFunc = (realWorld: RealWorld) =>
          (realWorld.copy(log = s"PUT $str" :: realWorld.log), ())

        State(stateFunc)
      }


      def readLine: Test[String] = {
        val stateFunc = (realWorld: RealWorld) =>
          (realWorld.copy(log = "READ" :: realWorld.log), "Bob")

        State(stateFunc)
      }
    }


  def greetSomeone[F[_]: Console: Monad]: F[Unit] = {
    for {
      _ <- Console[F].putStrLn("What is your name?")
      name <- Console[F].readLine
      _ <- Console[F].putStrLn(s"Hello, $name")
    } yield ()
  }

}
