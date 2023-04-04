package Graphs.ArticulationPoint_CutVertex;
import java.util.ArrayList;

// Tarjan’s algorithm
// MUST Watch : https://youtu.be/3t3JHswP7mw
// MUST Watch : https://youtu.be/j1QDfU21iZk (New, OP)
// Great Reading: https://www.scaler.com/topics/data-structures/articulation-points-and-bridges/
// Reading: https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
// https://codeforces.com/blog/entry/71146 (great)
// https://takeuforward.org/data-structure/articulation-point-in-graph-g-56/
// https://practice.geeksforgeeks.org/problems/articulation-point-1/1 (Problem Link)

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
    public boolean[] getAllArticulationPoints(int V, ArrayList<ArrayList<Integer>> adjList){
        // visited[] --> keeps track of visited vertices
        boolean[] visited = new boolean[V];

        // insertionTime[] or inTime[] --> Stores discovery/insertion time of visited vertices
        int[] insertionTime = new int[V];

        // The value lowestInsertionTime[v] indicates earliest visited vertex reachable from subtree rooted with v.
        // We store the information of the vertex with the lowest discovery time that we can access from a particular
        // vertex using a single back-edge
        // "It has info. of the minimum lowest time of insertion among all adjacent nodes apart from
        // parent and visited nodes."
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

    private void dfs(int node, int parent, int timer, boolean[] visited, int[] inTime, int[] lowInTime,
                     boolean[] isArticulation, ArrayList<ArrayList<Integer>> adjList) {
        // Mark the current node as visited
        visited[node] = true;

        // Initialize discovery/insertion time and earliest visited node reachable value
        // The value lowestInsertionTime[v] indicates earliest visited node reachable from subtree rooted with v.
        inTime[node] = lowInTime[node] = timer++;

        // This store the count of child node that are individual child (i.e, children of a node aren't
        // joined to each other by any path except the parent to child paths OR simply
        // children vertices which are disconnected from each other.)
        // There, must exits atleast two such children
        // This is done to handle only the case of starting node of DFS traversal (whose parent is -1)
        int individualChild = 0;

        // Go through all vertices adjacent to this
        for (int neighbour : adjList.get(node)){
            // we don't want to go back through the same path
            if (neighbour == parent) continue;

            // If 'v' is not visited yet, then make it a child of 'u' in DFS tree and recurse for it (in DFS)
            if (!visited[neighbour]){
                // If the adjacent child node is still unvisited, then this child node is individual
                // child node (i.e, child of a node aren't joined to each other by any path except
                // the parent to child paths)
                individualChild++;

                dfs(neighbour, node, timer, visited, inTime, lowInTime, isArticulation, adjList);

                // If the insertion time of the earliest node reachable from child node 'v'
                // is greater or equal to the insertion time of parent 'u' in DFS tree, then u is a cut-node
                // Now, try removing node "node"
                // If the below condition evaluates to false, it means "neighbour" can reach a node
                // before the timing of "node", so the "node" can't be Articulation point
                // If the below condition evaluates to true, then the "node" is an Articulation point
                // bcoz "neighbour" can't reach a node before the timing of "node"
                // We check only for vertices that are not the stating node of DFS
                if (lowInTime[neighbour] >= inTime[node]  &&  parent != -1)
                    isArticulation[node] = true;

                // Check if the subgraph rooted with 'v' (child) has a connection to one of the
                // ancestors of 'u' (parent). If it has such a connection, then update the lowInTime[] for 'u'
                lowInTime[node] = Math.min(lowInTime[node], lowInTime[neighbour]);
            }
            // Else if neighbour is visited, this means that we found an ancestor
            // update the value of lowInTime[node] to earliest visited node reachable from that node
            // Simply, finds the ancestor node with the least insertion time
            // The child_node in this case will be an already visited node. Thus, it is a back-edge.
            // See new video for this.
            else
                lowInTime[node] = Math.min(lowInTime[node], inTime[neighbour]);
        }

        // This condition is handled only for the starting node of DFS traversal (when DFS call is made in
        // previous function getAllArticulationPoints())
        // If 'node' is root/start of DFS traversal and has two or more individual children.
        if (parent == -1  &&  individualChild > 1)
            isArticulation[node] = true;
    }
}
