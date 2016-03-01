package com.eugene.impatience_scala

import scala.collection.mutable
import scala.io.Source


/**
  * Created by eugene on 16/3/1.
  */
object Chap4 {

    def main(args: Array[String]) {
        //1
        val t1 = Map("macbook"->10000, "iMac"->8888)
        val t1_2 = for((key,value) <- t1) yield (key,value*0.9)
        t1_2.foreach{case (k,v) => print(k+"->"+v+" ")}
        println()
        //2
        countWord()
    }

    def countWord() = {
        val strings = Source.fromFile("resources/test.txt").mkString
        val words = strings.split("\\s+")
        val wordMap = mutable.HashMap[String, Int]()
        for(word <- words) wordMap(word) = wordMap.getOrElse(word, 0)+1
        println(wordMap.mkString(", "))
    }

    

}
