package com.eugene.impatience_scala

/**
  * Created by eugene on 16/3/6.
  */
package object random {
    var seed:Int = _
    val a = BigDecimal(1664525)
    val b = BigDecimal(1013904223)
    val n = 32
    def nextInt():Int={
        val temp = (seed * a + b) % BigDecimal(2).pow(n)
        seed = temp.toInt
        seed
    }
    def nextDouble():Double={
        val temp = (seed * a + b) % BigDecimal(2).pow(n)
        seed = temp.toInt
        temp.toDouble
    }
}

object  T3 extends App{
    (1 to 10) foreach (_ => println(random.nextInt()))
    (1 to 10) foreach (_ => println(random.nextDouble()))
}