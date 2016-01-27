package com.eugene

/**
  * Created by eugene on 16/1/27.
  */
object HelloWorld {

  def main(args:Array[String]):Unit = {
    println("hello world")

    val x = 1 to 5

    x.foreach(print)
    println()
    x foreach print
    println()
    (5 to 1 by -1).foreach(print)
    println()
    10 to 5 by -1 foreach print
    println()
    (10 to 5 by -1) foreach print
    println()

    var i=0
    while (i<10) {
      print("i=" + i + " ")
      i+=1
    }
    println()

    showNumberInRange(1,14)
    println()

    val a = Array(1,2,3,4)
    println(a(3))

    val map = Map("fork"->"tenendor")
    map("fork") foreach print
    println()
    val safeMap = map.withDefaultValue("haha")
    safeMap("bottle")

    val s = Set("-1",2,7)
    println(String.valueOf(s(0)))
    println(s(2))

    val divideInts = (x:Int, y:Int)=>(x/y, x%y)
    val d = divideInts(10, 3)
    println(d._1)
    println(d._2)

    val add10:Int=>Int = _+10
    println(List(1,2,3) map add10)
  }

  def showNumberInRange(a:Int, b:Int):Unit = {
    print(a + " ")
    if (a<b) showNumberInRange(a+1, b)
  }

}
