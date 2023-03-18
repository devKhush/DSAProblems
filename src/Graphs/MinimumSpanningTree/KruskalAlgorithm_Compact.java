package Graphs.MinimumSpanningTree;
import java.util.ArrayList;
import java.util.Arrays;

// PRE_REQUISITE: DISJOINT SET DATA STRUCTURE
// Kruskal Algorithm is a Greedy Algorithm
// https://youtu.be/DMnDM_sxVig
// https://practice.geeksforgeeks.org/problems/minimum-spanning-tree/1
// https://takeuforward.org/data-structure/kruskals-algorithm-minimum-spanning-tree-g-47/

public class KruskalAlgorithm_Compact {
    /** *********************************** KRUSKAL ALGORITHM ***************************************
     * Intuition (Same as Prims Algorithm):
        * Always take Smaller weighted edges first

     * Steps:
     * 1. Sort all the edges in non-decreasing order of their weight.
     * 2. Pick the smallest edge. Check if it forms a cycle with the spanning tree formed so far.
            If cycle is not formed, include this edge. Else, discard it.
            This step uses a Disjoint Set Union Data Structure.

     * Time Complexity:     O(E*log(E)) + O(E)  =  O(E * log(E))
        * Srt all the 'E' edges
     * Space Complexity: O(V)
        * O(V) for Disjoint set (size[] & parent[] array)
     */
    int spanningTree(int V, int E, int[][] edges){
        // Sort all the edges
        Arrays.sort(edges, (a, b) -> (a[2] - b[2]));

        // MST edges and cost
        ArrayList<int[]> mstEdges = new ArrayList<>();
        int mstCost = 0;

        // Disjoint Set (union by size)
        DisjointSet ds = new DisjointSet(V);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int edgeWt = edge[2];

            if (ds.areDisjoint(u, v)) {
                ds.union(u, v);
                mstCost += edgeWt;
                mstEdges.add(new int[]{u, v});
            }
        }
        return mstCost;
    }


    /*************************** Disjoint Set: Union by Rank and Path Compression *************************
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    static class DisjointSet {
        int[] parent, size;
        public DisjointSet(int V) {
            this.parent = new int[V];
            this.size = new int[V];
            for (int i = 0; i < V; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int getParent(int u) {
            if (u == parent[u])
                return u;
            return parent[u] = getParent(parent[u]);
        }

        public void union(int u, int v) {
            int parentU = getParent(u);
            int parentV = getParent(v);
            if (parentU == parentV)
                return;

            if (size[parentU] > size[parentV]) {
                parent[parentV] = parentU;
                size[parentU] += size[parentV];
            } else {
                parent[parentU] = parentV;
                size[parentV] += size[parentU];
            }
        }

        public boolean areDisjoint(int u, int v) {
            return getParent(u) != getParent(v);
        }
    }
}
