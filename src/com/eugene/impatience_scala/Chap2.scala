package com.eugene.impatience_scala

/**
  * Created by eugene on 16/2/27.
  */
object Chap2 {

    def main(args: Array[String]) {
        println(signum(Integer.MIN_VALUE))

        val t2 = {}
        print(t2 + " ")
        println(t2.getClass)

        var x = {}
        var y = 0
        x = y=1
        println(x)

        for(i <- 10 to 0 by -1) print(i+" ")
        println()

        countdown(8)
        println()

        var t6 = 1L
        for(c <- "Hello") t6 *= c
        println(t6)

        var t7 = 1L
        "Hello".foreach(c => t7 *= c)
        println(t7)

        println(product("Hello"))

        println(productRecursive("Hello"))

        println(xnRecursive(5,3))
    }

    def signum(x:Int) = {
        if(x>0) 1 else if(x<0) -1 else 0
    }

    def countdown(n:Int):Unit = {
        for(i <- n to 0 by -1) print(i+" ")
    }

    def product(s:String) = {
        var res = 1L
        s.foreach(res *= _.toLong)
        res
    }

    def productRecursive(s:String):Long = {
        if(s.length==1) return s.head.toLong
        return s.head.toLong*productRecursive(s.tail)
    }

    def xnRecursive(x:Double, n:Int):Double = {
        var res = 0D
        if(n==0) res = 1
        else if(n<0) res = 1/xnRecursive(x, -n)
        else{
            if(n%2==0) res = math.pow(xnRecursive(x, n/2), 2)
            else res = x*xnRecursive(x, n-1)
        }
        res
    }
}
