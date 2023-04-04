package Graphs.BridgesInGraph_CutEdge;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// Tarjan’s algorithm
// https://youtu.be/CsGP_s_3GWg
// https://youtu.be/2rjZH0-2lhk
// https://youtu.be/qrAub5z8FeA (new)
// https://takeuforward.org/graph/bridges-in-graph-using-tarjans-algorithm-of-time-in-and-low-time-g-55/
// https://www.geeksforgeeks.org/bridge-in-a-graph/#:~:text=An%20edge%20in%20an%20undirected,increases%20number%20of%20disconnected%20components
// https://leetcode.com/problems/critical-connections-in-a-network/

/**
 * INTUITION: We do DFS traversal of the given graph. In DFS tree an edge (u, v) (u is parent of v in
         DFS tree) is bridge if there does not exist any other alternative paths to reach u or an
         ancestor of u from subtree rooted with v.

 * Though Process: Every parent vertex will ask its every child vertex, "If the edge between us is removed
        will you still be connected to the graph?"
        Child vertex can reply "yes" to the parent vertex only if it can reach a vertex before or equal to
        parent's timing via some other paths.
 * The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted with v.
   The condition for an edge(u, v) to be a bridge is, “lowestInsertionTime[v] > insertionTime[u]”.
 * A child 'v' (of vertex 'u') we must need to reach the vertex 'u' or an ancestor of 'u' via some paths
   other than edge from u to v (u <--> v) because edge u<-->v is to be deleted.
 * If from 'v' (child of vertex 'u') we can reach the vertex 'u' or an ancestor of 'u' via some paths
   other than edge from u to v (u <--> v). Then the edge from u to v is not a bridge.
 * If it is not possible to reach from 'v' to the vertex 'u' or an ancestor of 'u' via some paths other than edge
   from u to v. Then, the edge from u to v is a bridge.


 * Time Complexity  : O(V + E)
    * The above function is DFS with additional arrays. So time complexity is same as DFS
      which is O(V+E) for adjacency list representation of graph.
 * Space Complexity : O(V + E) + O(3 * V) = O(V)    We require 3 arrays
 */

public class BridgesInGraph_CutEdge {
    /************************************** DFS Solution ******************************************/
    public List<List<Integer>> findBridgesInGraph(int V, List<List<Integer>> connections){
        // Create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
        for (List<Integer> edge : connections){
            adj.get(edge.get(0)).add(edge.get(1));
            adj.get(edge.get(1)).add(edge.get(0));
        }

        // insertionTime[] or inTime[] --> Stores discovery/insertion time of visited vertices
        int[] inTime = new int[V];

        // The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted
        // with v, apart from parent
        // We store the information of the vertex with the lowest discovery time that we can access from a particular
        // vertex using a single back-edge
        // "It has info. of the minimum lowest time of insertion among all adjacent nodes apart from parent."
        int[] lowInTime = new int[V];

        // visited[] --> keeps track of visited vertices
        boolean[] visited = new boolean[V];

        // Output array to store all the bridges in graph
        List<List<Integer>> bridges  = new ArrayList<>();

        for (int u = 0; u < V; u++){
            if (!visited[u])
                dfs(u, -1, adj, visited, inTime, lowInTime, bridges);
        }
        return bridges;
    }

    private int timer = 1;

    private void dfs(int node, int parent, ArrayList<ArrayList<Integer>> adj, boolean[] visited, int[] inTime, int[] lowInTime, List<List<Integer>> bridges){
        // Mark the current node as visited
        visited[node] = true;

        // Initialize discovery/insertion time and earliest visited vertex reachable value
        // The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted with v.
        inTime[node] = lowInTime[node] = timer++;

        for (int neighbour : adj.get(node)){
            // we don't want to go back through the same path
            if (parent == neighbour) continue;

            if (!visited[neighbour]){
                dfs(neighbour, node, adj, visited, inTime, lowInTime, bridges);

                // Check if the subgraph rooted with 'v' (child) has a connection to one of the
                // ancestors of 'u' (parent). If it has such a connection, then update the lowInTime[] for 'u'
                lowInTime[node] = Math.min(lowInTime[node], lowInTime[neighbour]);

                // If the insertion time of earliest/lowest vertex reachable from vertex 'v' (child)
                // is greater than insertion time of 'u' (parent) in DFS tree, then u-v is a bridge
                // Now, try removing the edge between "vertex" & "adjacentVertex"
                // If the below condition evaluates to false, it means "adjacentVertex" can reach a vertex
                // before or equal to timing of "vertex", so the edge can't be bridge
                // If the below condition evaluates to true, then the edge is a bridge bcoz "adjacentVertex"
                // can't reach a vertex before or equal to timing of "vertex"
                if (lowInTime[neighbour] > inTime[node]){
                    bridges.add(Arrays.asList(node, neighbour));
                }
            }
            // Else if neighbour is visited, this means that we found an ancestor
            // update the value of lowInTime[vertex] to earliest visited vertex reachable from that vertex
            // Simply, finds the ancestor vertex with the least insertion time
            // The child_node in this case will be an already visited node. Thus, it is a back-edge.
            else{
                lowInTime[node] = Math.min(lowInTime[node], lowInTime[neighbour]);
            }
        }
    }
}
