package com.eugene.impatience_scala

/**
  * Created by eugene on 16/3/13.
  */
object Chap12 {
    def main(args: Array[String]) {
        //1
        println(values(x=>x*x, -5, 5).mkString(" "))
        println
        //2
        println(List(1,333,4,6,4,4,9,32,6,9,0,2).reduceLeft(math.max(_, _)))
        println
        //3
        println(factorial(-5))
        println(factorial(5))
        println
        //4
        println(factorial2(-3))
        println(factorial2(3))
        println
        //5
        println(largest(x => 10 * x - x * x, 1 to 10))
        println
        //6
        println(largestAt1(x => 10 * x - x * x, 1 to 10))
        println
        //7
        val x = adjustToPair(_ * _)((6, 7))
        println(x)
        val pairs = (1 to 10) zip (11 to 20)
        println(pairs)
        val y = pairs.map(adjustToPair(_ + _))
        println(y)
        println
        //8
        val a = Array("asd","df","abcd")
        val b = Array(3,2,4)
        val c = Array(3,2,1)
        println(a.corresponds(b)(_.length == _))
        println(a.corresponds(c)(_.length == _))
        println
        //9

        //10
        unless(false){println("Unless")}
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

    def largest(fun:(Int)=>Int, inputs:Seq[Int]) = {
        inputs.map(fun).max
    }

    def largestAt(fun:(Int)=>Int, inputs:Seq[Int]) = {
        inputs.map(x => (x, fun(x))).reduceLeft((x,y)=>if(x._2>y._2) x else y)._1
    }
    def largestAt1(fun:(Int)=>Int, inputs:Seq[Int]) = {
        inputs.reduce((a,b) => if(fun(a)>fun(b)) a else b)
    }

    def adjustToPair(fun:(Int, Int)=>Int) = {
        (x:(Int, Int))=>fun(x._1, x._2)
    }

    def unless(condition: => Boolean)(block: => Unit){
        if(!condition){block}
    }
}
