package Graphs.MostStonesRemovedWithSameRowOrColumn;

// https://youtu.be/OwMNX8SPavM
// https://takeuforward.org/data-structure/most-stones-removed-with-same-row-or-column-dsu-g-53/
// https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/description/

public class MostStonesRemovedWithSameRowOrColumn {
    /*************************************** Disjoint Set Solution **************************************
     * Intuition:
        * All stones that are in same row and columns are connected to each other.
        * We treat rows and columns as the nodes, and when we see any stones. We connect its row and column.
        * Now to remove maximum stones, only stone from each component should be kept.
     * Time Complexity: O(n) + O(rows + cols)
        * n -> number of stones
        * rows, cols -> no. of rows and columns in the grid
     * Space Complexity: O(rows + cols)
     */
    public int removeStones(int[][] stones) {
        // Find the size of the Grid
        int n = stones.length;
        int rows = 0, cols = 0;
        for (int[] node : stones){
            rows = Math.max(rows, node[0]);
            cols = Math.max(cols, node[1]);
        }

        // Disjoint set with Nodes as rows and columns
        DisjointSet ds = new DisjointSet(rows + 1 + cols + 1);

        // "Merge or Connect" the rows and columns associated with stones
        for (int[] node : stones){
            int rowNode = node[0];
            int colNode = node[1] + rows + 1;
            ds.union(rowNode, colNode);
        }

        // Find the number of components. All Stones in a single Component will have either same
        // row or column. Another way to find number of components os present in the blog solution
        int components = 0;
        for (int node = 0; node < rows + 1 + cols + 1; node++){

            // Even for a Component with single stone, its size will be 2; bcoz we merge its row and column
            if (ds.parent[node] == node  &&  ds.size[node] >= 2){
                components++;
            }
        }
        return n - components;
    }
}


class DisjointSet{
    int[] parent;
    int[] size;
    public DisjointSet(int V){
        parent = new int[V];
        size = new int[V];
        for (int i = 0; i < V; i++){
            size[i] = 1;
            parent[i] = i;
        }
    }
    public int getParent(int u){
        if (u == parent[u])
            return u;
        return parent[u] = getParent(parent[u]);
    }
    public void union(int u, int v){
        int uPar = getParent(u);
        int vPar = getParent(v);
        if (uPar == vPar) return;
        if (size[uPar] >= size[vPar]){
            parent[vPar] = uPar;
            size[uPar] += size[vPar];
        }
        else{
            parent[uPar] = vPar;
            size[vPar] += size[uPar];
        }
    }
}
