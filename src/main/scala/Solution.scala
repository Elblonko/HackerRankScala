/**
 * Created by elblonko on 10/14/14.
 */
/*
A crab is an undirected graph which has two kinds of vertices: 1 head, and K feet , and exactly K edges which join the head to each of the feet.( 1 <= K <= T, where T is given)

Given an undirected graph, you have to find in it some vertex-disjoint subgraphs where each one is a crab . The goal is to select those crabs in such a way that the total number of vertices covered by them is maximized.

Note: two graphs are vertex-disjoint if they do not have any vertex in common.

Input Format
The first line of input contains a single integer C. C test-cases follow. The first line of each test-case contains three integers N, T, and M (the number of nodes, max number of feet in the crab graph, and number of edges, respectively). Each of next M lines contains two space separated values v1i, v2i meaning that the there is an edge between vertexes v1i and v2i. Note that the graph doesn't have parallel edges or loops.

Output Format
For each test-case, output a single integer indicating the maximum number of vertices which can be covered by vertex disjoint sub-graphs of crab- graphs.

Constraints
1 <= C <= 10
2 <= T <= 100
2 <= N <= 100
0 <= M <= N * (N-1)/2
1 <= v1i <= N
1 <= v2i <= N

Sample Input
2
8 2 7
1 4
2 4
3 4
5 4
5 8
5 7
5 6
6 3 8
1 2
2 3
3 4
4 5
5 6
6 1
1 4
2 5

Sample Output
6
6

Explanation
Test #1: The graph for this test-case below. Because T = 2, each crab can have a maximum of 2 feet => each crab can cover a maximum of 3 nodes. We can cover 6 nodes of this graph with these two crabs: One of the crabs has 4 as its head and 1 and 3 as its feet, the other crab has 5 as its head and 7 and 8 as its feet. No additional crabs can be added.

The above is not a unique solution: any combination of two crabs, with one head at 4 and one head at 5, will suffice. We could have also chosen Head[4]feet[1,2] and Head[5]feet[6,7] as our two crabs.
 */

import java.util.Scanner

object Solution {

  //debug variable
  var debug = true

  //Can find if 2 nodes in a adjacency matrix are connected
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

  //Function to create undirected connection in a graph given two indices
  def createUndirectedConnection(nodeA:Int, nodeB:Int, graph: Array[Array[Int]]): Array[Array[Int]] = {
    //could easily pass in a weight for the connection
    graph(nodeA)(nodeB) = 1
    graph(nodeB)(nodeA) = 1

    graph
  }

  def main(args: Array[String]) {
    //println(canReach(0,1,graph2,Array.fill(graph2.size)(false) ) )


    val sc = new Scanner(System.in)
    //num Test Cases
    val numCases = sc.nextInt()
    /*
    Max nodes that can be covered is head plus maxFeet
     */
    //For loop over each test case passed in
    for(cases <- 1 to numCases) {
      //Read in N T and M
      val numNodes = sc.nextInt()
      val maxFeet = sc.nextInt()
      val numEdges = sc.nextInt()
      if (debug) {
        println()
        println("N T and M numNodes: " + numNodes + " MaxFeet: " + maxFeet + " numEdges: " + numEdges)
        println()
      }

      //now create the matrix with size of nodes n and 0 fill it
      def zeroFill (x:Int, y:Int) : Int ={
        0
      }
      var graph = Array.tabulate(numNodes,numNodes)(zeroFill)

      //check the array is built
      if(debug) {
        println("Is the Matrix built?")
        for (i <- graph.indices) {
          println()
          for (j <- graph(i).indices) {
            print(graph(i)(j) + " ")
          }
        }
      }

      //now get the edges
      //For loop over each line of input where i build the matrix connections
      for (i <- 1 to numEdges) {

        var nodeA = sc.nextInt() -1
        var nodeB = sc.nextInt() -1
        if(debug) {
          println()
          println("adding to graph at: " + nodeA + " " + nodeB + " i is: " + i)
          println()
        }
        graph = createUndirectedConnection(nodeA,nodeB,graph)

        if(debug) {
          for (i <- graph.indices) {
            println()
            for (j <- graph(i).indices) {
              print(graph(i)(j) + " ")
            }
          }
        }
      }
      //check the array is built

    }

  }
}
