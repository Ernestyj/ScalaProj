package com.eugene.impatience_scala

import scala.beans.BeanProperty

/**
  * Created by eugene on 16/3/2.
  */
object Chap5 {

    def main(args: Array[String]) {
        println("Hi")
    }

    class Counter{
        private var value = 0
        def increment() = {
            if(value<Int.MaxValue) value += 1
        }
        def current = value
    }

    class BankAccount{
        private var balance = 0d
        def money = balance
        def deposit(value:Double) = {
            balance += value
        }
        def withdraw(value:Double) = {
            if(value<=balance) balance -= value
        }
    }
    //class Time(hrs:Int, min:Int){
    //    val hours = hrs
    //    val minutes = min
    //    def before(other:Time):Boolean = {
    //        val thisTime = hours*60+minutes
    //        val thatTime = other.hours*60+other.minutes
    //        if(thisTime<thatTime) true else false
    //    }
    //}
    class Time(hrs:Int, min:Int){
        private val hoursMinutes = hrs*60
        val minutes = min
        def hours = hrs
        def before(other:Time):Boolean = {
            val thisTime = hoursMinutes+minutes
            val thatTime = other.hours*60+other.minutes
            if(thisTime<thatTime) true else false
        }
    }
    class Student{
        @BeanProperty var name = ""
        @BeanProperty var id = 0L
    }
//    class Person(var age:Int){
//        age = if(age<0) 0 else age
//    }
    class Person(string:String){
        val firstName = string.split(" ")(0)
        val lastName = string.split(" ")(1)
    }
    class Car(val manufactuer:String, val model: String,
              val year: Int = -1, var license: String = "")
    class Employee{
        val name:String = "John Q. Public"
        var salary:Double = 0
    }

}


