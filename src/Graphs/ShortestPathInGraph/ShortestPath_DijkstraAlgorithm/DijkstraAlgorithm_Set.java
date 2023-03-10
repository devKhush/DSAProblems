package Graphs.ShortestPathInGraph.ShortestPath_DijkstraAlgorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

// https://youtu.be/PATgNiuTP20
// https://takeuforward.org/data-structure/dijkstras-algorithm-using-set-g-33/

/*
* In Java, implementation of Dijkstra Algorithm using TreeSet (sorted set) is not possible.
* Because, TreeSet can consider only one element (in case of multiple element) for uniqueness and sorted-order.
* In C++, sets can consider one element for sorted-order and whole item for uniqueness.
* Time Complexity: O((V + E) * log(V))  ~  O(E * log(V))    when E >> V
* Not much difference in time complexity, nearly same as that of Priority Queue. Any of the method can be used.
* Space Complexity: O(V)
 */

public class DijkstraAlgorithm_Set {
}
