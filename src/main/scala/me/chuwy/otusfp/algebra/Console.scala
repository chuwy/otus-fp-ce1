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

  def apply[F[_]](implicit ev: Console[F]): Console[F] = ev

  implicit val interpreter: Console[IO] =
    ???

  implicit val testInterpreter: Console[Test] =
    ???

  def greetSomeone[F[_]: Console: Monad]: F[Unit] =
    ???

}
