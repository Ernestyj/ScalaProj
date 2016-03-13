package com.eugene.impatience_scala

/**
  * Created by eugene on 16/3/13.
  */
object Chap12 {
    def main(args: Array[String]) {
        //1
        println(values(x=>x*x, -5, 5).mkString(" "))
        //2
        println(List(1,333,4,6,4,4,9,32,6,9,0,2).reduceLeft(math.max(_, _)))
        //3
        println(factorial(-5))
        println(factorial(5))
        //4
        println(factorial2(-3))
        println(factorial2(3))
        //5

        //6

        //7

        //8

        //9

        //10
    }

    def values(fun:(Int)=>Int, low:Int, high:Int) = {
        var list = List[(Int, Int)]()
        low to high foreach {
            x =>
            list = (x, fun(x))::list
        }
        list
    }

    def factorial(n:Int) = {
        if (n==0) 1
        else if (n>0) {
            1 to n reduceLeft(_*_)
        }
    }
    def factorial2(n:Int) = {
        (1 to n).foldLeft(1)(_*_)
    }
}
