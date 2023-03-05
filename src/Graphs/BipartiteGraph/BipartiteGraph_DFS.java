package Graphs.BipartiteGraph;
import java.util.ArrayList;

// Note: In a Bipartite graph, we can color all the nodes with exactly 2 colors such that no two
// adjacent nodes have the same color
// There is also a Bookish definition of Bipartite Graph
// Note that it is possible to color a cycle graph with an even cycle using two colors
// But it is not possible to color a cycle graph with an odd cycle using two colors.

// https://youtu.be/uC884ske2uQ
// https://youtu.be/KG5YFfR0j8A
// https://takeuforward.org/data-structure/bipartite-check-using-dfs-if-graph-is-bipartite/
// https://www.geeksforgeeks.org/bipartite-graph/
// https://www.geeksforgeeks.org/check-if-a-given-graph-is-bipartite-using-dfs/
// This is the classical 2-coloring Problem (Recall M-coloring Problem, Back-tracking)

public class BipartiteGraph_DFS {
    /*
     * Time Complexity: O(V + 2E)     Same as DFS for Graph with adjacency list
     * Space Complexity: O(V)        Same as DFS for Graph with adjacency list
     */
    public boolean isBipartite(ArrayList<Integer>[] adjList) {
        int V = adjList.length;
        int[] color = new int[V];

        for (int vertex = 0; vertex < V; vertex++) {
            if (color[vertex] == 0) {
                if (!dfs(vertex, 1, adjList, color))
                    return false;
            }
        }
        return true;
    }


    public boolean dfs(int vertex, int colorWith, ArrayList<Integer>[] adjList, int[] color){
        color[vertex] = colorWith;

        for (int neighbour : adjList[vertex]){
            if (color[neighbour] == 0){
                int neighbourColor = color[vertex] == 1 ? 2 : 1;
                if (!dfs(neighbour, neighbourColor, adjList, color))
                    return false;
            }
            else if (color[vertex] == color[neighbour])
                return false;
        }
        return true;
    }
}
