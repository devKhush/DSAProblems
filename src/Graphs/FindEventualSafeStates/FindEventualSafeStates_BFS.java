package Graphs.FindEventualSafeStates;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://leetcode.com/problems/find-eventual-safe-states/description/
// https://youtu.be/2gtg3VsDGyc
// READ:
// https://takeuforward.org/data-structure/find-eventual-safe-states-bfs-topological-sort-g-25/

public class FindEventualSafeStates_BFS {
    /**************************************** BFS Solution ********************************************
     * Observation: Nodes that are the Part of a cycle OR has outgoing edge to any cycle,
        can never be safe nodes.
     * Intuition: Why reversing the edges worked?
        * Terminal nodes have 0 out-degree.
        * If we reverse the graph, terminal nodes will now have 0 in-degree.
        * Cyclic nodes will still remain in cycle.
        * Safe nodes (connected to terminal nodes) now can be reached via terminal node in reversed graph.
        * Only nodes in cycle or connected to a cycle in original graph won't be involved in topo sort.

     * Time Complexity: O(V+E) + O(V+E) + O(V) + O(V+E) + O(V*log(V))  ~  O(V+E)
        * Reverse_Graph, Calculate_InDegree, Add_0_InDegree_nodes, BFS_Time and Sorting_Graph_Nodes
     * Space Complexity: O(V+E) + O(V) + O(V)   ~  O(V+E)
        * Reversed_Graph_AdjList, InDegree_array and BFS_Queue
     */
    public List<Integer> eventualSafeNodes(int[][] adjList) {
        int V = adjList.length;
        ArrayList<Integer>[] revAdj = reverseGraph(V, adjList);         // reverse the Graph

        // Same Algorithm as Topo sort
        int[] inDegree = new int[V];
        for (int node = 0; node < V; node++){
            for (int neighbour : revAdj[node])
                inDegree[neighbour]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int node = 0; node < V; node++){
            if (inDegree[node] == 0)
                queue.add(node);
        }
        ArrayList<Integer> topoSort = new ArrayList<>();

        while (!queue.isEmpty()){
            int node = queue.remove();
            topoSort.add(node);

            for (int neighbour : revAdj[node]){
                inDegree[neighbour]--;
                if (inDegree[neighbour] == 0)
                    queue.add(neighbour);
            }
        }
        topoSort.sort((a, b) -> (a-b));
        return topoSort;
    }


    private ArrayList<Integer>[] reverseGraph(int V, int[][] adjList){
        ArrayList<Integer>[] revAdj = new ArrayList[V];
        for (int node = 0; node < V; node++){
            revAdj[node] = new ArrayList<>();
        }
        for (int node = 0; node < V; node++){
            for (int neighbour : adjList[node]){
                revAdj[neighbour].add(node);
            }
        }
        return revAdj;
    }
}
