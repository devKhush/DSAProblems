package Graphs.FindEventualSafeStates;
import java.util.ArrayList;
import java.util.List;

// PRE_REQUISITE: "Detect cycle in Directed graph"
// https://youtu.be/uRbJ1OF9aYM
// https://leetcode.com/problems/find-eventual-safe-states/description/
// https://takeuforward.org/data-structure/find-eventual-safe-states-dfs-g-20/

public class FindEventualSafeStates_DFS {
    /**************************************** DFS Solution ********************************************
     * Observation: Nodes that are the Part of a cycle OR has outgoing edge to any cycle,
            can never be safe nodes.

     * Time Complexity: O(V) + O(V + E)  ~  O(V + E)
        * DFS_time and loop
     * Space Complexity: O(V) + O(V) + O(V)  ~ O(V)
        * visited_array, path_visited_array and recursion_stacks_space
     */
    public List<Integer> eventualSafeNodes(int[][] adjList) {
        int V = adjList.length;
        ArrayList<Integer> safeNodes = new ArrayList<>();
        boolean[] pathVisited = new boolean[V];
        boolean[] visited = new boolean[V];

        for (int node = 0; node < V; node++){
            if (!visited[node])
                dfs(node, visited, pathVisited, adjList);
        }
        // Add those nodes to the answer that were the part of a cycle or had outgoing edge to the cycle
        // Add those nodes whose path couldn't be completed, due to presence of cycle
        for (int node = 0; node < V; node++){
            if (!pathVisited[node])
                safeNodes.add(node);
        }
        return safeNodes;
    }

    private boolean dfs(int node, boolean[] visited, boolean[] pathVisited, int[][] adjList){
        visited[node] = true;
        pathVisited[node] = true;

        for (int neighbour : adjList[node]){
            if (!visited[neighbour]){
                if (dfs(neighbour, visited, pathVisited, adjList))
                    return true;
            }
            else if (pathVisited[neighbour])
                return true;
        }
        pathVisited[node] = false;
        return false;
    }
}
