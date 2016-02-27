package com.eugene.impatience_scala

import scala.util.Random

/**
  * Created by eugene on 16/2/27.
  */
object Chap1 {

    def main(args: Array[String]) {
        val x = math.sqrt(3)
        println(3-x*x)

        val y = BigInt(2).pow(1024)
        println(y)

        val z = BigInt.probablePrime(100, Random);

        val a = BigInt(Random.nextInt()).toString(36)
        println(a)

        val s = "hello"
        println(s(0))
        println(s.take(1))
        println(s.takeRight(1))
        println(s.reverse(0))
    }

}
