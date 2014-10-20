/**
 * Created by elblonko on 10/19/14.
 */
import scala.collection.mutable

/**
 * Created by elblonko on 10/19/14.
 */

object AdjacencyMatrix {
  case class Edge (val dest:Vertex,val weight:Int)

  class Vertex {
    val edges = mutable.Buffer[Edge]()
  }

  //example of Array style graph
  val graph1 = Array.fill(4)(new Vertex)
  graph1(0).edges += Edge(graph1(1),2) += Edge(graph1(2),3)

  val graph2 = Array(Array(0,0,3,0),
    Array(0,0,5,4),
    Array(1,0,0,2),
    Array(1,0,0,0))


  def main(args:Array[String]){
    println(canReach(0,1,graph2,Array.fill(graph2.size)(false) ) )
  }

/*
This is a good algorithm for checking if something is in a graph via a adjacency matrxi representation
 */
  def canReach(curr:Int,dest:Int,graph:Array[Array[Int]], visited:Array[Boolean]):Boolean = {
    visited(curr) = true
    if(curr==dest) true
    else{
      var ret = false
      for (i <- graph(curr).indices){
        println("i= " + i + " Graph indices: " + graph(curr).indices)
        if( !visited(i) && graph(curr)(i)>0){
          ret = ret || canReach(i,dest,graph,visited)
        }
      }
      ret
      //This could also do what the for loop return does
      //graph(curr).indices.exists(i => graph(curr)(i)>0 && canReach(i,dest,graph))
    }
  }

}







