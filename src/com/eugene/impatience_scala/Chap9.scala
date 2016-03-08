package com.eugene.impatience_scala

import java.io.{File, PrintWriter}

import scala.io.Source

/**
  * Created by eugene on 16/3/8.
  */
object Chap9 {
    def main(args: Array[String]) {
//        t1()
//        t2()
//        t3()
//        t5()
    }
    def t1() = {
        val path = "resources/test.txt"
        val source = Source.fromFile(path, "utf-8")
        val reverseLines = source.getLines().toArray.reverse
        source.close()
        val pw = new PrintWriter(path)
        reverseLines.foreach(line => pw.write(line+"\n"))
        pw.close()
    }
    def t2() = {
        val path = "resources/test.txt"
        val source2 = Source.fromFile(path, "utf-8")
        val lines = source2.getLines()
        val newStrings = for(line <- lines) yield line.replaceAll("\\s+", "*")
//        println(newStrings.mkString(" "))
        val pw2 = new PrintWriter(path)
        for(string <- newStrings) println(string.getClass)
        pw2.flush()
        pw2.close()
    }
    def t3() = {
        val path = "resources/test.txt"
        val strings = Source.fromFile(path, "utf-8").mkString.split("\\s+")
        strings.foreach(string => if(string.length>12) println(string))
    }
    def t5() = {
        val path = "resources/nums.txt"
        val pw = new PrintWriter(path)
        for(i <- 0 to 20) pw.write("%.0f %f\n".format(math.pow(2, i), 1/math.pow(2, i)))
        pw.flush()
        pw.close()
    }
}
