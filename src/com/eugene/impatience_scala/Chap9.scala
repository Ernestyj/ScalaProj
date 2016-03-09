package com.eugene.impatience_scala

import java.io._

import scala.collection.mutable.ArrayBuffer
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
//        t9()
//        t10()
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
    def t9() = {
        var count = 0
        for(d <- subdirs(new File("."))) {
            d.listFiles().filter(_.isFile).foreach(f => if(f.getName.endsWith(".class")) println(f.getName))
            count += d.listFiles().filter(_.isFile).count(_.getName.endsWith(".class"))
        }
        print(count)
    }
    def subdirs(dir:File):Iterator[File] = {
        val children = dir.listFiles.filter(_.isDirectory)
        children.toIterator ++ children.toIterator.flatMap(subdirs _)
    }

    class Person(var name:String) extends Serializable{
        val friends = new ArrayBuffer[Person]()
        def addFriend(friend : Person){
            friends += friend
        }
        override def toString() = {
            var str = "My name is " + name + " and my friends name is "
            friends.foreach(str += _.name + ",")
            str
        }
    }
    def t10() = {
        val p1 = new Person("Ivan")
        val p2 = new Person("F2")
        val p3 = new Person("F3")
        p1.addFriend(p2)
        p1.addFriend(p3)
        println(p1)
        val out = new ObjectOutputStream(new FileOutputStream("person.obj"))
        out.writeObject(p1)
        out.close()
        val in =  new ObjectInputStream(new FileInputStream("person.obj"))
        val p = in.readObject().asInstanceOf[Person]
        println(p)
    }

}
