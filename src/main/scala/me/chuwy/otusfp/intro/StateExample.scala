package me.chuwy.otusfp.intro

import cats.data.State

object StateExample {

  case class World(int: Int)

  type TinyIO[A] = State[World, A]

}
