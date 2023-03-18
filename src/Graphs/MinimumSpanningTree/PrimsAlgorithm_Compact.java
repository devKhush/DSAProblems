package Graphs.MinimumSpanningTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// https://takeuforward.org/data-structure/prims-algorithm-minimum-spanning-tree-c-and-java-g-45/
// https://youtu.be/mJcZjjKzeqk
// https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1

public class PrimsAlgorithm_Compact {
    /***************************************** BFS Dijkstra Algorithm ***********************************
     * Intuition: Add all the edges with the min. edge first, so that MST can be constructed.
        * MST always has minimum weighted edges, so always take min. weighted edges first
        * The intuition of this algorithm is the greedy technique used for every node.
        * If we carefully observe, for every node, we are greedily selecting its unvisited adjacent
            node with the minimum edge weight(as the priority queue here is a min-heap and the
            topmost element is the node with the minimum edge weight).
            Doing so for every node, we can get the sum of all the edge weights of the minimum spanning
            tree and the spanning tree itself(if we wish to) as well.

     * Time Complexity: O(E * log(E))
        * While loop for MST will run for O(E * log(E))
        * Max size of minheap will be O(E), removal and addition will take O(E * log(E)) time
     * Space complexity: O(V) + O(E)
        * O(V) for parent_array and mstParentIncluded
        * Max size of minheap will be O(E), when all the edges are connected.
     */
    public int primsAlgorithm_V2(int V, ArrayList<Vertex>[] adj){
        // "mstParent[]" indicates the parent of a particular vertex in the MST
        int[] mstParent =  new int[V];
        Arrays.fill(mstParent, -1);

        // Boolean array which indicates whether a vertex is already included in MST or not
        boolean[] mstIncluded = new boolean[V];

        // Running similar to Dijkstra algo
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int start = 0;
        minHeap.add(new int[]{start, 0});

        // Sum of all Edge weights will be the Cost of construction of MST
        int mstCost = 0;

        while (!minHeap.isEmpty()){
            int node = minHeap.peek()[0];
            int edgeWt = minHeap.remove()[1];

            if (mstIncluded[node])
                continue;

            mstIncluded[node] = true;
            mstCost += edgeWt;

            for (Vertex neighbour : adj[node]){
                if (!mstIncluded[neighbour.node]){
                    mstParent[neighbour.node] = node;
                    minHeap.add(new int[]{neighbour.node, neighbour.edgeWt});
                }
            }
        }

        System.out.println("Edges in MST are:");
        for (int vertex = 0; vertex < V; vertex++) {
            if (vertex != start)
                System.out.println(vertex + " <---> " + mstParent[vertex]);
        }
        return mstCost;
    }



    static class Vertex{
        int node;         // Stores destination vertex in adjacency list
        int edgeWt;       // Stores weight of  "vertex <--> destination_vertex" in the adjacency list
        public Vertex(int node, int edgeWt) {
            this.node = node;
            this.edgeWt = edgeWt;
        }
    }
}
