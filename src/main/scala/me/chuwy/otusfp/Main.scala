package me.chuwy.otusfp

import scala.io.StdIn

import cats.effect.{IOApp, IO}

import me.chuwy.otusfp.intro.StateExample

object Main extends IOApp.Simple {
  def greeting: IO[Unit] =
    for {
      _    <- IO.delay(println("What is name?"))
      name <- IO.delay(StdIn.readLine())
      _    <- IO.delay(println(s"Hello, $name"))
    } yield ()

  def run: IO[Unit] =
    IO.println(StateExample.finalResult)
}
