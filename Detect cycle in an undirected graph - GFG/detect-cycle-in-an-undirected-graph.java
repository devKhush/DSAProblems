// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            boolean ans = obj.isCycle(V, adj);
            if (ans)
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
}// } Driver Code Ends


class Solution {
    // BFS Solution **********************************************************************
    public boolean cycleCheck_BFS(int vertex, int V, ArrayList<ArrayList<Integer>> adjList, boolean[] visited){
        Queue<VertexPair> queue = new ArrayDeque<>();

        queue.add(new VertexPair(vertex, -1));
        visited[vertex] = true;

        while (!queue.isEmpty()){
            VertexPair currVertex = queue.remove();

            for (int adjacentVertex : adjList.get(currVertex.vertex)){
                if (visited[adjacentVertex]  &&  adjacentVertex != currVertex.parentVertex)
                    return true;
                if (!visited[adjacentVertex]){
                    queue.add(new VertexPair(adjacentVertex, currVertex.vertex));
                    visited[adjacentVertex] = true;
                }
            }
        }
        return false;
    }
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];

        for (int vertex = 0; vertex < V; vertex++)
            if (!visited[vertex]  &&  cycleCheck_BFS(vertex, V, adj, visited))
                return true;
        return false;
    }    
    
    
    
    // DFS Solution **********************************************************************
    public boolean dfs(int vertex, int V, ArrayList<ArrayList<Integer>> adjList, boolean[] visited, int prevVertex){
        visited[vertex] = true;
        
        for (int adjacentVertex : adjList.get(vertex)){
            if (visited[adjacentVertex] &&  adjacentVertex != prevVertex)
                return true;
            if (!visited[adjacentVertex] && dfs(adjacentVertex, V, adjList, visited, vertex))
                return true;
        }
        return false;
    }
    public boolean isCycle_DFS(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        
        for (int vertex = 0; vertex < V; vertex++)
            if (!visited[vertex]  &&  dfs(vertex, V, adj, visited, -1))
                return true;
        return false;
    }
    
    static class VertexPair{
        int vertex, parentVertex;
        public VertexPair(int vertex, int parentVertex) {
            this.vertex = vertex;
            this.parentVertex = parentVertex;
        }
    }
}