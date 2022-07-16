// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String[] S = br.readLine().trim().split(" ");
            int V = Integer.parseInt(S[0]);
            int E = Integer.parseInt(S[1]);
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for(int i = 0; i < V; i++){
                adj.add(new ArrayList<Integer>());
            }
            for(int i = 0; i < E; i++){
                String[] s = br.readLine().trim().split(" ");
                int u = Integer.parseInt(s[0]);
                int v = Integer.parseInt(s[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            boolean ans = obj.isBipartite(V, adj);
            if(ans)
                System.out.println("1");
            else System.out.println("0");
       }
    }
}// } Driver Code Ends


class Solution{
    public boolean isBipartite(int V, ArrayList<ArrayList<Integer>>adjList){
        int[] color = new int[V];

        for (int vertex = 0; vertex < V; vertex++)
            if (color[vertex] == 0) {
                color[vertex] = 1;

                if (!canDoTwoColoring_DFS(vertex, adjList, color, V))
                    return false;
            }
        return true;
    }
    
    public boolean canDoTwoColoring_DFS(int vertex, ArrayList<ArrayList<Integer>> adjList, int[] color, int V){
        for (int adjacentVertex : adjList.get(vertex)){
            if (color[adjacentVertex] == 0){
                color[adjacentVertex] = color[vertex] == 1 ? 2 : 1;

                if (!canDoTwoColoring_DFS(adjacentVertex, adjList, color, V))
                    return false;
            }
            else if (color[vertex] == color[adjacentVertex])
                return false;
        }
        return true;
    }
}