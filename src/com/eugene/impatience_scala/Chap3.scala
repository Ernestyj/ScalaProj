package com.eugene.impatience_scala

import java.awt.datatransfer.{DataFlavor, SystemFlavorMap}
import java.util.TimeZone

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * Created by eugene on 16/3/1.
  */
object Chap3 {

    def main(args: Array[String]) {
        //1
        for (x <- randomArray(8)) print(x + " ")
        println()
        //2
        for (x <- exchange(Array(1, 2, 3, 4, 5, 6))) print(x + " ")
        println()
        //3
        for (i <- exchange2(Array(1, 2, 3, 4, 5))) print(i + " ")
        println()
        //4
        for (i <- positiveAhead(Array(1, -2, 3, -4, 5, 0))) print(i + " ")
        println()
        //5
        println(averageOfArray(Array(0, 1, 2, 3, 4, 5)))
        //6
        val t6 = Array(0, 1, 2, 3, 4, 5)
        t6.reverse.foreach(print(_))
        println()
        val tBuffer = ArrayBuffer(0, 1, 2, 3, 4, 5)
        tBuffer.reverse.foreach(print(_))
        println()
        //7
        for (x <- Array(1, 2, 3, 4, 4, 4, 5, 5, 6).distinct) print(x + " ")
        println()
        //8
        for (x <- removeRestNegative(Array(1, -2, 3, 4, -4, 4, -5, -5, 6))) print(x + " ")
        println()
        for (x <- removeRestNegative2(Array(1, -2, 3, 4, -4, 4, -5, -5, 6))) print(x + " ")
        println()
        val begin1 = System.currentTimeMillis()
        for(i <- 0 to 10000) removeRestNegative(Array(1, -2, 3, 4, -4, 4, -5, -5, 6))
        val end1 = System.currentTimeMillis()
        val begin2 = System.currentTimeMillis()
        for(i <- 0 to 10000) removeRestNegative2(Array(1, -2, 3, 4, -4, 4, -5, -5, 6))
        val end2 = System.currentTimeMillis()
        println((end1-begin1)+":"+(end2-begin2))//第二种方式更快
        //9
        val t9 = TimeZone.getAvailableIDs()
        val res9 = t9.filter(_.startsWith("America")).map(_.substring(8)).sorted
        for(x <- res9) print(x+" ")
        println()
        //10
        val flavors = SystemFlavorMap.getDefaultFlavorMap().asInstanceOf[SystemFlavorMap]
        val res = flavors.getNativesForFlavor(DataFlavor.imageFlavor)
        println(res)
    }

    def randomArray(n: Int): Array[Int] = {
        val array = for (i <- 0 to n) yield Random.nextInt(n)
        array.toArray
    }

    def exchange(array: Array[Int]): Array[Int] = {
        if (array == null) return null
        for (i <- 0 until(array.length, 2)) {
            if (i + 1 < array.length) {
                val temp = array(i)
                array(i) = array(i + 1)
                array(i + 1) = temp
            }
        }
        array
    }

    def exchange2(array: Array[Int]): Array[Int] = {
        if (array == null) return null
        val arr = for (i <- 0 until array.length) yield {
            if (i % 2 == 0) {
                if (i + 1 < array.length) array(i + 1)
                else array(i)
            } else {
                array(i - 1)
            }
        }
        arr.toArray
    }

    def positiveAhead(array: Array[Int]): Array[Int] = {
        if (array == null) return null
        val positive = ArrayBuffer[Int]()
        val zero = ArrayBuffer[Int]()
        val negative = ArrayBuffer[Int]()
        for (x <- array) {
            if (x > 0) positive.append(x)
            else if (x == 0) zero.append(x)
            else negative.append(x)
        }
        positive ++= zero
        positive ++= negative
        positive.toArray
    }

    def averageOfArray(array: Array[Double]): Double = {
        array.sum / array.length
    }

    def removeRestNegative(array: Array[Int]): Array[Int] = {
        val buffer = array.toBuffer
        var first = true
        val indexes = for (i <- 0 until buffer.length if first || buffer(i) >= 0) yield {
            if (buffer(i) < 0) first = false
            i
        }
        for (j <- 0 until indexes.length) buffer(j) = buffer(indexes(j))
        buffer.trimEnd(buffer.length - indexes.length)
        buffer.toArray
    }

    def removeRestNegative2(array: Array[Int]): Array[Int] = {
        val buffer = array.toBuffer
        val indexes = for(i <- 0 until array.length if buffer(i)<0) yield i
        val reversed = indexes.reverse
        for(i <- 0 until reversed.length-1){
            buffer.remove(reversed(i))
        }
        buffer.toArray
    }

}
