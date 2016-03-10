package com.eugene.impatience_scala

import java.awt.{Point, Rectangle}
import java.awt.geom.Ellipse2D
import java.io.{FileInputStream, InputStream}

/**
  * Created by eugene on 16/3/10.
  */
object Chap10 {

    def main(args: Array[String]) {
//        t1
//        t4
        t9
    }

    trait RectangleLike{
        this:Ellipse2D.Double =>
        def translate(x:Double, y:Double) = {
            this.x = x
            this.y = y
        }
        def grow(x:Double, y:Double) = {
            this.x += x
            this.y += y
        }
    }
    def t1 = {
        val egg = new Ellipse2D.Double(5, 10, 20, 30) with RectangleLike
        println("x = " + egg.getX + " y = " + egg.getY)
        egg.translate(10,-10)
        println("x = " + egg.getX + " y = " + egg.getY)
        egg.grow(10,20)
        println("x = " + egg.getX + " y = " + egg.getY)
    }

    class OrderedPoint extends Point with Ordered[Point]{
        override def compare(that: Point): Int = {
            if (this.x <= that.x && this.y < that.y) -1
            else if (this.x == that.x && this.y == that.y) 0
            else 1
        }
    }

    trait Loggers{
        def log(str:String, key:Int = 3):String
    }
    class CryptoLogger extends Loggers{
        override def log(str: String, key: Int): String = {
            for (i <- str) yield
                if (key >= 0) (97 + ((i - 97 + key)%26)).toChar
                else (97 + ((i - 97 + 26 + key)%26)).toChar
        }
    }
    def t4 = {
        val plain = "chenzhen";
        println("明文为：" + plain);
        println("加密后为：" + new CryptoLogger().log(plain));
        println("加密后为：" + new CryptoLogger().log(plain,-3));
    }

    trait PropertyChange extends java.beans.PropertyChangeSupport
    def t5 = {
//        val p = new Point() with PropertyChange
    }

    trait Logger {
        def log(msg: String)
    }
    trait PrintLogger extends Logger {
        def log(msg: String) = println(msg)
    }
    trait Buffering {
        this: InputStream with Logger =>
        val BUF_SIZE: Int = 5
        private val buf = new Array[Byte](BUF_SIZE)
        private var bufsize: Int = 0
        private var pos: Int = 0
        override def read(): Int = {
            if (pos >= bufsize) {
                bufsize = this.read(buf, 0, BUF_SIZE)
                log("buffered %d bytes: %s".format(bufsize, buf.mkString(", ")))
                if (bufsize > 0) -1
                pos = 0
            }
            pos += 1
            buf(pos-1)
        }
    }
    def t9 = {
        val f = new FileInputStream("resources/nums.txt") with PrintLogger with Buffering
        for(i <- 1 to 10) println(f.read())
    }

    class IterableInputStream extends InputStream with Iterable[Byte]{
        class InStreamIterator(outer:IterableInputStream) extends Iterator[Byte]{
            override def hasNext: Boolean = outer.available()>0
            override def next(): Byte = outer.read().toByte
        }
        override def read(): Int = ???
        override def iterator: Iterator[Byte] = new InStreamIterator(this)
    }
}
