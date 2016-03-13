package com.eugene.impatience_scala

import scala.collection.mutable.ArrayBuffer

/**
  * Created by eugene on 16/3/12.
  */
object Chap11 {
    def main(args: Array[String]) {
//        t3()
//        t4()
//        t5()
//        t6()
//        t7()
        t8()
    }

    class Fraction(n:Int, d:Int){
        private val num:Int = if(d==0) 1 else n * sign(d)/gcd(n,d);
        private val den:Int = if(d==0) 0 else d * sign(d)/gcd(n,d);
        override def toString = num + "/" + den
        def sign(a:Int) = if(a > 0) 1 else if (a < 0) -1 else 0
        def gcd(a:Int,b:Int):Int = if(b==0) math.abs(a) else gcd(b, a%b)
        def +(other:Fraction) = {
            new Fraction(num*other.den+other.num*den, den*other.den)
        }
        def /(other:Fraction) = {
            new Fraction(num*other.den, den*other.num)
        }
    }
    def t3() = {
        val f = new Fraction(15,-6)
        val p = new Fraction(20,60)
        println(f)
        println(p)
        println(f+p)
        println(f/p)
    }

    class Money(val dollar:Int, val cent:Int){
        def +(other:Money) = {
            val c = (cent + other.cent) % 100
            val d = (cent + other.cent) / 100
            Money(dollar+other.dollar+d, c)
        }
        def -(other:Money) = {
            val c = (this.toCent - other.toCent) % 100
            val d = (this.toCent - other.toCent) / 100
            Money(d, c)
        }
        def ==(other:Money) = {
            dollar==other.dollar && cent==other.cent
        }
        def <(other:Money) = {
            if (dollar<other.dollar) true
            else if (dollar==other.dollar) {
                if (cent<other.cent) true else false
            } else false
        }
        override def toString = "dollar = " + dollar + " cent = " + cent
        private def toCent = dollar*100+cent
    }
    object Money{
        def apply(dollar:Int, cent:Int): Money ={
            new Money(dollar, cent)
        }
    }
    def t4() = {
        val m1 = Money(1,200)
        val m2 = Money(2,2)
        println(m1 + m2)
        println(m1 - m2)
        println(m1 == m2)
        println(m1<m2)
        println(Money(1,75)+Money(0,50))
        println(Money(1,75)+Money(0,50)==Money(2,25))
    }

    class Table{
        private var s:String = ""
        def |(str:String):Table={
            val t = Table()
            t.s = this.s + "<td>" + str + "</td>"
            t
        }
        def ||(str:String):Table={
            val t = Table()
            t.s = this.s + "</tr><tr><td>" + str + "</td>"
            t
        }
        override def toString():String={
            "<table><tr>" + this.s + "</tr></table>"
        }
    }
    object Table{
        def apply() = new Table
    }
    def t5(): Unit ={
        println(Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM,.NET")
    }

    class ASCIIArt(str:String){
        val arr:ArrayBuffer[ArrayBuffer[String]] = new ArrayBuffer[ArrayBuffer[String]]()
        if (str != null && !str.trim.eq("")){
            str.split("[\r\n]+").foreach{
                line =>
                    val s = new ArrayBuffer[String]()
                    s += line
                    arr += s
            }
        }
        def this(){
            this("")
        }
        def +(other:ASCIIArt):ASCIIArt={
            val art = new ASCIIArt()
            val length = if (this.arr.length >= other.arr.length) this.arr.length else other.arr.length
            for(i <- 0 until length){
                val s = new ArrayBuffer[String]()
                val thisArr:ArrayBuffer[String] = if (i < this.arr.length) this.arr(i) else new ArrayBuffer[String]()
                val otherArr:ArrayBuffer[String] = if (i < other.arr.length) other.arr(i) else new ArrayBuffer[String]()
                thisArr.foreach(s += _)
                otherArr.foreach(s += _)
                art.arr += s
            }
            art
        }
        def *(other:ASCIIArt):ASCIIArt={
            val art = new ASCIIArt()
            this.arr.foreach(art.arr += _)
            other.arr.foreach(art.arr += _)
            art
        }
        override def toString()={
            var ss:String = ""
            arr.foreach{
                ss += _.mkString(" ") + "\n"
            }
            ss
        }
    }
    def t6() = {
        val a = new ASCIIArt(""" /\_/\
                               |( ' ' )
                               |(  -  )
                               | | | |
                               |(__|__)
                               |""".stripMargin)
        val b = new ASCIIArt( """    -----
                                |  / Hello \
                                | <  Scala |
                                |  \ Coder /
                                |    -----
                                |""".stripMargin)
        println(a + b * b)
        println("*********************")
        println((a + b) * b)
        println("*********************")
        println(a * b)
    }

    class BitSequence(private var value:Long = 0){
        implicit def bool2int(b: Boolean) = if (b) 1 else 0
        def update(loc: Int, newVal: Int) = value |= (newVal & 1L) << loc % 64
        def apply(loc: Int): Int = if ((value & 1L << loc % 64) > 0) 1 else 0
        override def toString = "%64s".format(value.toBinaryString)
    }
    def t7() = {
        val x = new BitSequence()
        println(x)
        x(5) = 1
        x(63) = 1
        x(64) = 1
        println(x(5))
        println(x)
        x(63) = 0
        println(x)
        val y = new BitSequence(99L)
        println(y)
    }

    class Matrix(val row:Int, val col:Int){
        private val matrix = Array.ofDim[Double](row, col)

        def this() = this(2, 2)
        def this(n:Int) = this(n, n)

        def +(other:Matrix) = {
            require(row==other.row)
            require(col==other.col)
            var res = new Matrix(row, col)
            for(i <- 0 until row; j <- 0 until col) {
                res(i, j) = this.matrix(i)(j) + other.matrix(i)(j)
            }
            res
        }
        def *(other:Matrix) = {
            require(col == other.row)
            val res = new Matrix(row, col)
            for(i <- 0 until row; j <- 0 until col) {
                res(i, j) = prod(other, i, j)
            }
            res
        }
        def *(factor: Double) = {
            val res = new Matrix(row, col)
            for(i <- 0 until row; j <- 0 until col) {
                res(i, j) = this.matrix(i)(j) * factor
            }
            res
        }
        def -(other: Matrix) = this + other * -1

        def apply(m:Int, n:Int) = matrix(m)(n)
        def update(m:Int, n:Int, v:Double) = matrix(m)(n) = v

        override def toString = matrix.map(_.mkString(" ")).mkString("\n")

        private def prod(other: Matrix, i: Int, j: Int) = {
            (for (k <- 0 until col) yield matrix(i)(k) * other.matrix(k)(j)).sum
        }
    }
    def t8() = {
        val x = new Matrix()
        x(0, 0) = 1
        x(0, 1) = 2
        x(1, 0) = 3
        x(1, 1) = 4
        println(x)
        println()
        println(x*x)
        println()
        println(x * 2)
        println()
        println(x * 2 - x)
        println()
        println((x * 2) * (x * 3))
    }

    class RichFile(val path:String) extends java.io.File(path)
    object RichFile{
        def unapply(richFile:RichFile): Option[(String, String)] = {
            val path = richFile.path
            val pos = path.lastIndexOf("/")
            if (pos == -1) None else Some((path.substring(0, pos), path.substring(pos+1)))
        }
        def unapplySeq(s:String):Option[Seq[String]] = {
            if (s.trim=="") None else Some(s.trim.split("/"))
        }
    }


}
