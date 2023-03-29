package Graphs.NumberOfIslands_II;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/Rn6B-Q4SNyA
// MUST READ:
// https://takeuforward.org/graph/number-of-islands-ii-online-queries-dsu-g-51/
// https://practice.geeksforgeeks.org/problems/number-of-islands/1

public class NumberOfIslands_II {
    /************************************ Disjoint Set Solution **************************************
     * Time Complexity: O(4 * n)
        * n is size of operators
     * Space Complexity: O(rows*cols)
        * Disjoint_Set_Size + Visited_Array
     */
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        DisjointSet ds = new DisjointSet(rows * cols);       // Disjoint Set
        int[][] visited = new int[rows][cols];                  // Visited array

        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};

        ArrayList<Integer> ans = new ArrayList<>();
        int numIslands = 0;

        for (int[] location : operators){
            int i = location[0];
            int j = location[1];
            int node = cols * i + j;

            if (visited[i][j] == 1) {
                ans.add(numIslands);
                continue;
            }
            visited[i][j] = 1;
            numIslands++;
            for (int a = 0; a < 4; a++){
                int newI = i + di[a];
                int newJ = j + dj[a];
                int neighbour = cols * newI + newJ;
                if (newI >= 0 && newJ >= 0 && newI < rows && newJ < cols){
                    if (visited[newI][newJ] == 1  &&  ds.areDisjoint(node, neighbour)){
                        numIslands--;
                        ds.union(node, neighbour);
                    }
                }
            }
            ans.add(numIslands);
        }
        return ans;
    }
}

class DisjointSet{
    int[] parent;
    int[] size;
    public DisjointSet(int V) {
        parent = new int[V];
        size = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int getParent(int u){
        if (u == parent[u])
            return u;
        return parent[u] = getParent(parent[u]);
    }
    public void union(int u, int v){
        int parU = getParent(u);
        int parV = getParent(v);
        if (parU == parV)
            return;

        if (parent[parU] > parent[parV]){
            parent[parV] = parU;
            size[parU] += size[parV];
        }
        else{
            parent[parU] = parV;
            size[parV] += size[parU];
        }
    }
    public boolean areDisjoint(int u, int v){
        return getParent(u) != getParent(v);
    }
}
