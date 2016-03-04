package com.eugene.impatience_scala

import java.awt.Point

/**
  * Created by eugene on 16/3/3.
  */
object Chap6 extends App{

    println("***"+args.reverse.mkString(" "))
    println(Poker.Spade.toString)
    for( c <- RGB.values ) println("0x%06x: %s".format(c.id, c))

    object Conversions{
        def inchesToCentimeters(value:Double) = value*2.54
        def gallonsToLiters(value:Double) = value*3.78541178
        def milesToKilometers(value:Double) = value*1.609344
    }
    class UnitConversion(val factor:Double){
        def convert(value:Double) = factor*value
    }
    object InchesToCentimeters extends UnitConversion(2.54)
    object GallonsToLiters extends UnitConversion(3.78541178)
    object MilesToKilometers extends UnitConversion(1.609344)
//        object Origin extends Point{
//            override def getLocation:Point = super.getLocation
//        }
    class Point(x:Double, y:Double)
    object Point{
        def apply(x:Double, y:Double) = new Point(x, y)
    }
    object Poker extends Enumeration{
        type Poker = Value
        val Spade = Value("♣")
        val Club = Value("♣")
        val Heart = Value("♥")
        val Diamond = Value("♦")
        override def toString = Poker.values.mkString(" ")
        def isRed(card:Poker) = card==Heart || card==Diamond
    }
    object RGB extends Enumeration{
        val black = Value(0x000000, "Black")
        val red = Value(0xff0000, "Red")
        val green = Value(0x00ff00, "Green")
        val blue = Value(0x0000ff, "Blue")
        val yellow = Value(0xffff00, "Yellow")
        val magenta = Value(0xff00ff, "Magenta")
        val cyan = Value(0x00ffff, "Cyan")
        val white = Value(0xffffff, "White")
    }

}
