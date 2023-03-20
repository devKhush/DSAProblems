package Graphs.NumberOfOperationsToMakeNetworkConnected;

// PRE_REQUISITE: Disjoint-Set
// https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/
// https://youtu.be/FYrl7iz9_ZU
// https://takeuforward.org/data-structure/number-of-operations-to-make-network-connected-dsu-g-49/

public class NumberOfOperationsToMakeNetworkConnected {
    /************************************* Disjoint Set Solution ****************************************
     * Time Complexity: O(V + E)
        * O(E) to add edges to disjoint-set
        * O(V) to find connected components
     * Space Complexity: O(V)
        * Disjoint-set array
     */
    public int makeConnected(int V, int[][] connections) {
        DisjointSet ds = new DisjointSet(V);
        int availableEdges = 0;
        int components = 0;

        for (int[] edge : connections) {
            int u = edge[0];
            int v = edge[1];
            if (ds.areDisjoint(u, v))
                ds.union(u, v);
            else
                availableEdges++;
        }
        for (int u = 0; u < V; u++) {
            if (ds.parent[u] == u)
                components++;
        }
        return components - 1 > availableEdges ? -1 : components - 1;
    }



    // Disjoint-Set Data Structure
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
