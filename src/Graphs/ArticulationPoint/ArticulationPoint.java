package Graphs.ArticulationPoint;
import java.util.ArrayList;

// Tarjan’s algorithm
// MUST Watch : https://youtu.be/3t3JHswP7mw
// Great Reading: https://www.scaler.com/topics/data-structures/articulation-points-and-bridges/
// Reading: https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
// https://codeforces.com/blog/entry/71146

/**
 * Definition: In DFS traversal, a vertex 'u' is articulation point if one of the following two
    conditions is true.
    * 'u' is root of DFS traversal (starting vertex of DFS Traversal) and it has at least two children
      which are not connected to each other by any means (directly & indirectly). See Video for "parent == -1"
    * 'u' is not root of DFS traversal & it has a child 'v' such that no vertex in subtree rooted
      with 'v' has a back edge to one of the ancestors (in DFS tree) of u.


 * INTUITION: We do DFS traversal of the given graph. In DFS tree a vertex 'u' (u, v) (u is parent of v in
    DFS tree) is cut-vertex, iff there does not exist any other alternative paths to reach an
    ancestor of u from subtree rooted with v. (node V does not have a back-edge to any of the ancestors of U)


 * Though Process: Every parent vertex will ask its every child vertex, "If I get disappeared, will you
        still be connected to the graph?"
        Child vertex can reply "yes" to the parent vertex only if it can reach a vertex before the
        parent's timing via some other paths.
 * The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted with v.
    The condition for a vertex to be cut-vertex is, “lowestInsertionTime[v] >= insertionTime[u]”.
    This is different from in case of cut-edges, because from a child 'v' (of vertex 'u') we must need to
    reach the ancestor of 'u', because whole 'u' is to be deleted.
 * If from 'v' (child of vertex 'u') we can reach an ancestor of 'u' via some paths
    other than edge from u to v (u <--> v). Then, the vertex 'u' is not an Articulation point.
 * If it is not possible to reach from 'v' to an ancestor of 'u' via some paths other than edge
    from u to v. Then, the vertex 'u' is an Articulation point.


 * Time Complexity  : O(V + E)
 * The above function is DFS with additional arrays. So time complexity is same as DFS
    which is O(V+E) for adjacency list representation of graph.
 * Space Complexity : O(3 * V) = O(V)    We require 3 arrays
    Instead of boolean array condition "!visited[vertex]", we can also use condition "insertionTime[vertex]"
    to do the same in O(2*V) space
 */

public class ArticulationPoint {
    private void dfs(int vertex, int parent, int timer, boolean[] visited, int[] inTime, int[] lowInTime, boolean[] isArticulation, ArrayList<ArrayList<Integer>> adjList) {
        // Mark the current node as visited
        visited[vertex] = true;

        // Initialize discovery/insertion time and earliest visited vertex reachable value
        // The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted with v.
        inTime[vertex] = lowInTime[vertex] = timer++;

        // This store the count of child vertex that are individual child (i.e, children of a vertex aren't
        // joined to each other by any path except the parent to child paths OR simply
        // children vertices which are disconnected from each other.)
        // There, must exits atleast two such children
        // This is done to handle only the case of starting vertex of DFS traversal (whose parent is -1)
        int individualChild = 0;

        // Go through all vertices adjacent to this
        for (int adjacentVertex : adjList.get(vertex)){
            // we don't want to go back through the same path
            if (adjacentVertex == parent) continue;

            // If 'v' is not visited yet, then make it a child of 'u' in DFS tree and recurse for it (in DFS)
            if (!visited[adjacentVertex]){
                // If the adjacent child vertex is still unvisited, then this child node is individual
                // child vertex (i.e, child of a vertex aren't joined to each other by any path except
                // the parent to child paths)
                individualChild++;

                dfs(adjacentVertex, vertex, timer, visited, inTime, lowInTime, isArticulation, adjList);

                // If the insertion time of the earliest vertex reachable from child vertex 'v'
                // is greater or equal to the insertion time of parent 'u' in DFS tree, then u is a cut-vertex
                // Now, try removing vertex "vertex"
                // If the below condition evaluates to false, it means "adjacentVertex" can reach a vertex
                // before the timing of "vertex", so the "vertex" can't be Articulation point
                // If the below condition evaluates to true, then the "vertex" is an Articulation point
                // bcoz "adjacentVertex" can't reach a vertex before the timing of "vertex"
                // We check only for vertices that are not the stating vertex of DFS
                if (lowInTime[adjacentVertex] >= inTime[vertex]  &&  parent != -1)
                    isArticulation[vertex] = true;

                // Check if the subgraph rooted with 'v' (child) has a connection to one of the
                // ancestors of 'u' (parent). If it has such a connection, then update the lowInTime[] for 'u'
                lowInTime[vertex] = Math.min(lowInTime[vertex], lowInTime[adjacentVertex]);

            }
            // Else if neighbour is visited, this means that we found an ancestor
            // update the value of lowInTime[vertex] to earliest visited vertex reachable from that vertex
            // Simply, finds the ancestor vertex with the least insertion time
            // The child_node in this case will be an already visited node. Thus, it is a back-edge.
            else
                lowInTime[vertex] = Math.min(lowInTime[vertex], inTime[adjacentVertex]);
        }

        // This condition is handled only for the starting vertex of DFS traversal (when DFS call is made in
        // previous function getAllArticulationPoints())
        // If 'vertex' is root/start of DFS traversal and has two or more individual children.
        if (parent == -1  &&  individualChild > 1)
            isArticulation[vertex] = true;
    }


    public boolean[] getAllArticulationPoints(int V, ArrayList<ArrayList<Integer>> adjList){
        // visited[] --> keeps track of visited vertices
        boolean[] visited = new boolean[V];

        // insertionTime[] or inTime[] --> Stores discovery/insertion time of visited vertices
        int[] insertionTime = new int[V];

        // The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted with v.
        // We store the information of the vertex with the lowest discovery time that we can access from a particular
        // vertex using a single back-edge
        int[] lowestInsertionTime = new int[V];

        // Array to indicate which vertex is Articulation point
        // We are using Hashing (using array) to indicate Articulation point (cut-vertex), because
        // same cut-vertex can be discovered multiple times
        boolean[] isArticulation = new boolean[V];

        // Call DFS
        for (int vertex = 0; vertex < V; vertex++)
            if (!visited[vertex])
                dfs(vertex, -1, 1, visited, insertionTime, lowestInsertionTime, isArticulation, adjList);

        return isArticulation;
    }
}
