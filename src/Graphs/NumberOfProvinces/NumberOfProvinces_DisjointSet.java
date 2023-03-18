package Graphs.NumberOfProvinces;

// https://youtu.be/ZGr5nX-Gi6Y
// https://leetcode.com/problems/number-of-provinces/description/
// https://takeuforward.org/data-structure/number-of-provinces-disjoint-set-g-48/

public class NumberOfProvinces_DisjointSet {
    /********************************* Disjoint Set Solution *****************************************
     * Intuition:
        * Assume all nodes to be initially disconnect
        * Traverse the graph (adj. matrix) and connect it and count connected components.

     * Time Complexity: O(V*V)
        * Disjoint set takes almost constant time
     * Space Complexity: O(V)
     */
    public int findCircleNum(int[][] adj) {
        int V = adj.length;
        int components = V;
        DisjointSet ds = new DisjointSet(V);

        for (int u = 0; u < V; u++){
            for (int v = 0; v < V; v++){
                if (adj[u][v] == 1  &&  ds.areDisjoint(u, v)){
                    ds.union(u, v);
                    components--;
                }
            }
        }
        return components;
    }


    /************************************ Disjoint Set ************************************
     * Intuition: After forming Disjoint set from Graph, only one node in each component (parent node)
        will be the parent of itself.
     * Time Complexity: O(V*V)
        * Disjoint set takes almost constant time
     * Space Complexity: O(V)
     */
    int numProvinces(int[][] adj, int V) {
        DisjointSet ds = new DisjointSet(V);

        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (adj[u][v] == 1) {
                    ds.union(u, v);
                }
            }
        }
        int components = 0;
        for (int i = 0; i < V; i++) {
            if (ds.getParent(i) == i)
                components++;
        }
        return components;
    }


    // Disjoint Set: Union by size
    static class DisjointSet{
        int[] parent, size;
        public DisjointSet(int V){
            parent = new int[V];
            size = new int[V];
            for (int i = 0; i < V; i++){
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
            int parentU = getParent(u);
            int parentV = getParent(v);
            if (parentU == parentV)
                return;
            if (size[parentU] > size[parentV]){
                parent[parentV] = parentU;
                size[parentU] += size[parentV];
            }
            else{
                parent[parentU] = parentV;
                size[parentV] += size[parentU];
            }
        }

        public boolean areDisjoint(int u, int v){
            return getParent(u) != getParent(v);
        }
    }
}
