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
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                // adj.get(v).add(u);
            }
            Solution obj = new Solution();
            ArrayList<Integer> ans = obj.bfsOfGraph(V, adj);
            for (int i = 0; i < ans.size(); i++)
                System.out.print(ans.get(i) + " ");
            System.out.println();
        }
    }
}
// } Driver Code Ends


class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        int[][] graph = new int[V][V];

        for (int i = 0; i < V; i++)        
            for (int neighbour : adj.get(i))
                    graph[i][neighbour] = 1;
        
        boolean[] visited = new boolean[V];

        ArrayList<Integer> bfsTraversal = new ArrayList<>();

        // for (int i = 0; i < V; i++){
        //     if (!visited[i])
        //         bfs(i, V, graph, visited, bfsTraversal);
        // }
        bfs(0, V, graph, visited, bfsTraversal);
        
        return bfsTraversal;
    }

    public void bfs(int node, int V, int[][] adjMatrix, boolean[] visited, ArrayList<Integer> bfs){
         Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(node);
        visited[node] = true;

        while (!bfsQueue.isEmpty()){
            int currentVertex = bfsQueue.remove();
            bfs.add(currentVertex);

            for (int neighbourVertex = 0; neighbourVertex < V; neighbourVertex++)
                if (adjMatrix[currentVertex][neighbourVertex] != 0  &&  !visited[neighbourVertex]){
                    visited[neighbourVertex] = true;
                    bfsQueue.add(neighbourVertex);
                }
        }
    }
}