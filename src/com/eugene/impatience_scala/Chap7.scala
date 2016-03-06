package com.eugene.impatience_scala

package com {
    class T1{}
    package eugene{
        class T2(t1:T1){}
        package impatience{
            class T3(t1:T1, t2:T2){}
        }
    }
}
package com.eugene.impatience{
//    class T4(t1:T1){} //can not find type T1
}

/**
  * Created by eugene on 16/3/4.
  */
object Chap7 {
    
    def main(args: Array[String]) {
        //6
        import java.util.{HashMap => JavaHashMap}
        val javaMap = new JavaHashMap[Int, String]
        javaMap.put(1, "One")
        javaMap.put(2, "Two")
        javaMap.put(3, "Three")
        import collection.mutable.{HashMap => ScalaHashMap, Map => ScalaMap}
        val scalaMap = ScalaHashMap[Int, String]()
        for(key <- javaMap.keySet().toArray)
            scalaMap += (key.asInstanceOf[Int] -> javaMap.get(key))
        println(scalaMap.mkString(" "))
        //9
        import java.lang.System._
        var password = Console.readLine()
        if(password.equals("secret")) out.println("Hello " + getProperty("user.name"))
        else err.println("password error")
    }

}

