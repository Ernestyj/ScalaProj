package com.eugene.impatience_scala

/**
  * Created by eugene on 16/3/18.
  */
object Chap14 {

    def main(args: Array[String]) {
        //2
        println(swap((1,2)))
        println
        //3
        println(swap(Array("1","2","3","4")).mkString)
        println
        //4
        val p = price(Multiple(10,Article("Blackwell Toster",29.95)))
        println(p)
        println
        //5
        val l: List[Any] = List(List(3, 8), 2, List(5))
        println(leafSum(l))
        println
        //6
        val r = BinNode(Leaf(3),BinNode(Leaf(3),Leaf(9)))
        println(leafSumBin(r))
        println
        //7
        val x = MultNode(MultNode(Leaf(3), Leaf(8)), Leaf(2), MultNode(Leaf(5)))
        println(leafSum(x))
        println
        //8
        val y = Node('+', Node('*', Leaf(3), Leaf(8)), Leaf(2),  Node('-', Leaf(5)))
        println(eval(y))
        println
        //9
        //10
    }

    def swap(tup:(Int, Int)) = {
        tup match {
            case (a, b) => (b, a)
        }
    }

    def swap(arr:Array[String]) = {
        arr match {
            case Array(first, second, rest @ _*) => Array(second, first)++rest
            case _ => arr
        }
    }

    abstract class Item
    case class Multiple(num : Int,item : Item) extends Item
    case class Article(description : String , price : Double) extends Item
    case class Bundle(description : String , discount : Double , item : Item*) extends Item
    def price(it : Item) : Double = it match {
        case Article(_,p) => p
        case Bundle(_,disc,its @ _*) => its.map(price _).sum - disc
        case Multiple(n,it) => n * price(it)
    }

    def leafSum(list:List[Any]):Int = {
        var total = 0
        list.foreach(lst => {
            lst match {
                case l:List[Any] => total += leafSum(l)
                case i:Int => total += i
            }
        })
        total
    }

    sealed abstract class BinaryTree
    case class Leaf(value : Int) extends BinaryTree
    case class BinNode(left : BinaryTree, right : BinaryTree) extends BinaryTree
    def leafSumBin(tree:BinaryTree):Int = tree match {
        case Leaf(v) => v
        case BinNode(l, r) => leafSumBin(l)+leafSumBin(r)
    }

    case class MultNode(tr: BinaryTree*) extends BinaryTree
    def leafSum(tree:BinaryTree):Int = tree match {
        case Leaf(v) => v
        case MultNode(all @_*) => all.map(leafSum).sum
    }

    case class Node(op: Char, leafs: BinaryTree*) extends BinaryTree
    def eval(tree:BinaryTree):Int = tree match {
        case Leaf(v) => v
        case Node(op, leafs @_*) => op match {
            case '+' => leafs.map(eval).sum
            case '-' => -leafs.map(eval).sum
            case '*' => leafs.map(eval).product
        }
    }
}
