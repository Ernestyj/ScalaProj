package com.eugene.impatience_scala

import java.util
import java.util.Calendar

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.immutable
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
        countWordMutable()
        //3
        countWordImmutable()
        //4
        countWordSorted()
        //5
        countWordJavaTreeMap
        //6
        mapDates
        //7
        printJavaSysProperty
        println()
        //8
        println(minmax(Array(0,5,7,8,3,4,2,1)))
        //9
        println(lteqgt(Array(0,5,7,8,3,4,2,1), 4))
        //10
        println("Hello".zip("World"))
    }

    def countWordMutable() = {
        val strings = Source.fromFile("resources/test.txt").mkString
        val words = strings.split("\\s+")
        val wordMap = mutable.HashMap[String, Int]()
        for(word <- words) wordMap(word) = wordMap.getOrElse(word, 0)+1
        println(wordMap.mkString(", "))
    }

    def countWordImmutable() = {
        val strings = Source.fromFile("resources/test.txt").mkString
        val words = strings.split("\\s+")
        var wordMap = immutable.HashMap[String, Int]()
        for(word <- words) wordMap += (word -> (wordMap.getOrElse(word, 0)+1))
        println(wordMap.mkString(", "))
    }

    def countWordSorted() = {
        val strings = Source.fromFile("resources/test.txt").mkString
        val words = strings.split("\\s+")
        var wordMap = immutable.TreeMap[String, Int]()
        for(word <- words) wordMap += (word -> (wordMap.getOrElse(word, 0)+1))
        println(wordMap.mkString(", "))
    }

    def countWordJavaTreeMap = {
        val strings = Source.fromFile("resources/test.txt").mkString
        val words = strings.split("\\s+")
        var wordMap = new util.TreeMap[String, Int]().asScala
        for(word <- words) wordMap += (word -> (wordMap.getOrElse(word, 0)+1))
        println(wordMap.mkString(", "))
    }

    def mapDates = {
        val daysMap = mutable.LinkedHashMap(
            "Monday" -> Calendar.MONDAY,
            "Tuesday" -> Calendar.TUESDAY,
            "Wednesday" -> Calendar.WEDNESDAY,
            "Thursday" -> Calendar.THURSDAY,
            "Friday" -> Calendar.FRIDAY,
            "Saturday" -> Calendar.SATURDAY,
            "Sunday" -> Calendar.SUNDAY)
        println(daysMap.mkString(", "))
    }

    def printJavaSysProperty = {
        val sysPropertyMap = System.getProperties.asScala
        val maxLen = sysPropertyMap.keySet.map(_.length).max
        for((k,v) <- sysPropertyMap) printf("%-" + maxLen + "s | %s\n", k, v)
    }

    def minmax(values:Array[Int]) = {
        (values.min, values.max)
    }

    def lteqgt(values:Array[Int], v:Int) = {
        (values.count(_<v), values.count(_==v), values.count(_>v))
    }

}
