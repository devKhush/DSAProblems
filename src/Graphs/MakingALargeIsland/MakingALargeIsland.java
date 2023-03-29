package Graphs.MakingALargeIsland;
import java.util.HashSet;

// https://youtu.be/lgiz0Oup6gM
// https://leetcode.com/problems/making-a-large-island/description/
// https://takeuforward.org/data-structure/making-a-large-island-dsu-g-52/

public class MakingALargeIsland {
    /***************************************** Disjoint Set Solution **********************************
     * Intuition:
        * If we can somehow group all the islands in the grid, then we can check the island size of each
            location in grid.
     * Time Complexity: O(4*n*n + 4*n*n)  ~  O(4*n*n)
     * Space Complexity: O(n*n)
     */
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);
        int[] di = {1, -1, 0, 0};
        int[] dj = {0, 0, 1, -1};

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    int node = n*i + j;
                    for (int a = 0; a < 4; a++){
                        int newI = i + di[a];
                        int newJ = j + dj[a];
                        int neighbour = newI*n + newJ;
                        if (newI >= 0 && newJ >= 0 && newI < n && newJ < n && grid[newI][newJ] == 1){
                            ds.union(node, neighbour);
                        }
                    }
                }
            }
        }
        int largestSize = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (grid[i][j] == 1){
                    largestSize = Math.max(largestSize, ds.size[ds.getParent(n*i + j)]);
                }
                else{
                    HashSet<Integer> allParents = new HashSet<>();
                    int size = 0;
                    for (int a = 0; a < 4; a++){
                        int newI = i + di[a];
                        int newJ = j + dj[a];
                        int neighbour = newI*n + newJ;
                        if (newI >= 0  &&  newJ >= 0  &&  newI < n  &&  newJ < n  &&
                                grid[newI][newJ] == 1  &&  !allParents.contains(ds.getParent(neighbour))){
                            allParents.add(ds.getParent(neighbour));
                            size += ds.size[ds.getParent(neighbour)];
                        }
                    }
                    largestSize = Math.max(largestSize, size + 1);
                }
            }
        }
        return largestSize;
    }
}

// "Disjoint Set": Union-By-Size
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

