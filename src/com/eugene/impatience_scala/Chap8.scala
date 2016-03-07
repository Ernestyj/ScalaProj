package com.eugene.impatience_scala

import java.awt.Point

import com.sun.tools.javac.jvm.Items

import scala.collection.mutable.ArrayBuffer

/**
  * Created by eugene on 16/3/7.
  */
object Chap8 {

    def main(args: Array[String]) {

    }

    class BankAccount(initialBalance:Double){
        private var balance = initialBalance
        def deposit(amount:Double) = { balance += amount; balance}
        def withdraw(amount:Double) = {balance -= amount; balance}
    }
    class CheckingAccount(initialBalance:Double) extends BankAccount(initialBalance){
        override def deposit(amount:Double) = super.deposit(amount-1)
        override def withdraw(amount:Double) = super.withdraw(amount+1)
    }
    class SavingsAccount(initialBalance:Double) extends BankAccount(initialBalance){
        private var freeCount = 1
        private var balance = initialBalance
        override def deposit(amount:Double) = {
            val fee = if(freeCount<=3) 0 else 1
            freeCount += 1
            balance += (amount-fee)
            balance
        }
        override def withdraw(amount:Double) = {
            val fee = if(freeCount<=3) 0 else 1
            freeCount += 1
            balance -= (amount+fee)
            balance
        }
        def earnMonthlyInterest = {
            balance += balance*0.02
            freeCount = 1
        }
    }

    abstract class Item{
        def price:Double
        def description:String
        override def toString = {
            "description:"+description+"price:"+price
        }
    }
    class SimpleItem(val price:Double, val description:String) extends Item
    class Bundle(items:ArrayBuffer[Item], d:String) extends Item{
        val itemsList = items
        override def price: Double = {
            var sum = 0d
            itemsList.foreach(sum += _.price)
            sum
        }
        override val description: String = itemsList.mkString(" ")
        def addItem(item:Item) = {
            itemsList += item
        }
    }

    abstract class Shape{
        def centerPoint:(Double, Double)
    }
    class Rectangle(startX:Int,startY:Int,endX:Int,endY:Int) extends Shape{
        override def centerPoint: (Double, Double) = ???
    }
    class Circle(x:Int,y:Int,radius:Double) extends Shape{
        override def centerPoint: (Double, Double) = ???
    }

    class Square(point:Point, width:Int) extends
            java.awt.Rectangle(point.x,point.y,width,width){
        def this() = this(new Point(0, 0), 0)
        def this(width:Int) = this(new Point(0, 0), width)
    }
}
