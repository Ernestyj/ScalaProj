package com.eugene.impatience_scala

import scala.collection.immutable
import scala.collection.mutable

/**
  * Created by eugene on 16/3/16.
  */
object Chap13 {
    def main(args: Array[String]) {
        //1
        println(mapStrIndex("Mississippi"))
        println
        //2
        println(mapStrIndex1("Mississippi"))
        println
        //3
        println(getridOfZero(mutable.LinkedList(3,25,0,2,0,0)))
        println
        //4
        println(filterMap(Set("Tom","Fred","Harry"),Map("Tom"->3,"Dick"->4,"Harry"->5)).mkString(","))
        println
        //5
        println(myMkString(Set("Tom","Fred","Harry"), ","))
        println
        //6
        val lst = List(1,2,3,4,5)
        println((lst :\ List[Int]())(_ :: _))
        println((List[Int]() /: lst)(_ :+ _))
        println((lst :\ List[Int]())((i,list)=>list:+i))
        println((List[Int]() /: lst)((list,i)=>i::list))
        //7

        //8

        //9

        //10
        
    }

    def mapStrIndex(str:String) = {
        var indexMap = mutable.HashMap[Char, mutable.SortedSet[Int]]()
        var i = 0
        str.foreach(
            c => {
                indexMap += (c -> (indexMap.getOrElse(c, mutable.SortedSet(i))+=i))
                i += 1
            }
        )
        indexMap
    }

    def mapStrIndex1(str:String) = {
        var indexMap = immutable.HashMap[Char, mutable.SortedSet[Int]]()
        var i = 0
        str.foreach(
            c => {
                indexMap += (c -> (indexMap.getOrElse(c, mutable.SortedSet(i))+=i))
                i += 1
            }
        )
        indexMap
    }

    def getridOfZero(list:mutable.LinkedList[Int]) = {
        list.filter(_!=0)
    }

    def filterMap(set:immutable.Set[String], map:immutable.Map[String, Int]) = {
        set.flatMap(map.get(_))
    }

    def myMkString(it:Iterable[String], split:String="") = {
        if (it!=Nil) it.reduceLeft(_+split+_)
    }
}
