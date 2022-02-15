package me.chuwy.otusfp.intro

import cats.data.State

object StateExample {

  object RealWorld

  type IO[A] = State[RealWorld.type, A]

  case class World(int: Int)

  case class Machine(candies: Int, coins: Int)

  type TinyIO[A] = State[World, A]


  def foo(s: World): (World, String) =
    (s.copy(int = s.int + 1), s.toString)

  val fooState: State[World, String] = State(foo)

  val result: State[World, String] = for {
    a <- fooState
    b <- fooState
    c <- fooState
    d <- fooState
  } yield a + b + c + d

  val finalResult = result.run(World(0)).value

  var i: Int = 0

}
