// { Driver Code Starts
// Initial Template for Java
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
            ArrayList<ArrayList<Integer>> adj =
                new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            ArrayList<Integer> ans = obj.dfsOfGraph(V, adj);
            for (int i = 0; i < ans.size(); i++)
                System.out.print(ans.get(i) + " ");
            System.out.println();
        }
    }
}
// } Driver Code Ends


class Solution {
    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[V];
        ArrayList<Integer> dfsTraversal = new ArrayList<>();
        dfs_iterative(0, visited, adjList, dfsTraversal);
        return dfsTraversal;
    }
    
    public void dfs_iterative(int vertex, boolean[] visited, ArrayList<ArrayList<Integer>> adjList, ArrayList<Integer> dfs){
        Stack<Integer> stack = new Stack<>();
        stack.add(vertex);

        while (!stack.isEmpty()){
            int currentVertex = stack.pop();
            if (!visited[currentVertex]){
                dfs.add(currentVertex);
                visited[currentVertex] = true;
            }

            for (int i = adjList.get(currentVertex).size() - 1; i >= 0; i--){
                int adjacentVertex = adjList.get(currentVertex).get(i);
                if (!visited[adjacentVertex])
                    stack.add(adjacentVertex);
            }
        }
    }
    
    public void dfs(int vertex, int[][] adjMatrix, int V, boolean[] visited, ArrayList<Integer> dfs){
        visited[vertex] = true;
        dfs.add(vertex);

        for (int adjacentVertex = 0; adjacentVertex < V; adjacentVertex++){
            boolean isAdjacentVertex = vertex != adjacentVertex  &&  adjMatrix[vertex][adjacentVertex] != 0;
            if (isAdjacentVertex  &&  !visited[adjacentVertex])
                dfs(adjacentVertex, adjMatrix, V, visited, dfs);
        }
    }
}