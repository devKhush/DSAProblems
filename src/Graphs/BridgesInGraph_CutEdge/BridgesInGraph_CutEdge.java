package Graphs.BridgesInGraph_CutEdge;
import java.util.ArrayList;

// https://youtu.be/CsGP_s_3GWg
// https://youtu.be/2rjZH0-2lhk
// https://www.geeksforgeeks.org/bridge-in-a-graph/#:~:text=An%20edge%20in%20an%20undirected,increases%20number%20of%20disconnected%20components

/**
 * INTUITION: We do DFS traversal of the given graph. In DFS tree an edge (u, v) (u is parent of v in
         DFS tree) is bridge if there does not exist any other alternative paths to reach u or an
         ancestor of u from subtree rooted with v.
 * The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted with v.
   The condition for an edge(u, v) to be a bridge is, “lowestInsertionTime[v] > insertionTime[u]”.

 * Time Complexity  : O(V + E)
    * The above function is DFS with additional arrays. So time complexity is same as DFS
      which is O(V+E) for adjacency list representation of graph.
 * Space Complexity : O(3 * V) = O(V)    We require 3 arrays
 */

public class BridgesInGraph_CutEdge {
    private int timer = 1;

    private void dfs(int vertex, int parent, ArrayList<ArrayList<Integer>> adjList, int[] inTime, int[] lowInTime, boolean[] visited, ArrayList<int[]> bridges){
        // Mark the current node as visited
        visited[vertex] = true;

        // Initialize discovery/insertion time and earliest visited vertex reachable value
        // The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted with v.
        inTime[vertex] = lowInTime[vertex] = timer++;

        // Go through all vertices adjacent to this
        for (int adjacentVertex : adjList.get(vertex)){
            if (adjacentVertex == parent)
                continue;

            // If 'v' is not visited yet, then make it a child of 'u' in DFS tree and recurse for it (in DFS)
            if (!visited[adjacentVertex]){
                dfs(adjacentVertex, vertex, adjList, inTime, lowInTime, visited, bridges);

                // If the insertion time of earliest/lowest vertex reachable from vertex 'v' (child)
                // is greater than insertion time of 'u' (parent) in DFS tree, then u-v is a bridge
                if (lowInTime[adjacentVertex] > inTime[vertex])
                    bridges.add(new int[]{vertex, adjacentVertex});

                // Check if the subgraph rooted with 'v' (child) has a connection to one of the
                // ancestors of 'u' (parent). If it has such a connection, then update the lowInTime[] for 'u'
                lowInTime[vertex] = Math.min(lowInTime[adjacentVertex], lowInTime[vertex]);
            }
            // Else if neighbour is visited, update the value of lowInTime[vertex] to earliest visited
            // vertex reachable from that vertex's neighbour
            else
                lowInTime[vertex] = Math.min(inTime[adjacentVertex], lowInTime[vertex]);

        }
    }

    public ArrayList<int[]> findBridgesInGraph(int V, ArrayList<ArrayList<Integer>> adjList){
        ArrayList<int[]> allBridges = new ArrayList<>();

        // visited[] --> keeps track of visited vertices
        boolean[] visited = new boolean[V];

        // insertionTime[] or inTime[] --> Stores discovery/insertion time of visited vertices
        int[] insertionTime = new int[V];

        // The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted with v.
        int[] lowestInsertionTime = new int[V];

        // Call DFS
        for (int vertex = 0; vertex < V; vertex++){
            if (!visited[vertex])
                dfs(vertex, -1, adjList, insertionTime, lowestInsertionTime, visited, allBridges);
        }
        // Output array to store all the bridges in graph
        return allBridges;
    }
}
