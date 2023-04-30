package Graphs.RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable;

public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable_Best {
    /***************************************** Solution 2 *********************************************
     * Another solution using shorter code and "Modified version of Disjoint Set"

     * Intuition:
        * We will connect those edges in graph first that can be travel by both of them, to maximize
            the count of edges that can be deleted.
        * Greedy way of taking as low as possible.

     * Time Complexity: O(2*n) ~ O(n)
     * Time Complexity: O(4*n) ~ O(n)
     * Two set for Alice and Bob
     */
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        DisjointSet alice = new DisjointSet(n);
        DisjointSet bob = new DisjointSet(n);
        int usedEdges = 0;

        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (alice.areDisjoint(edge[1], edge[2]) || bob.areDisjoint(edge[1], edge[2])) {
                    alice.union(edge[1], edge[2]);
                    bob.union(edge[1], edge[2]);
                    usedEdges++;
                }
            }
        }
        for (int[] edge : edges) {
            if (edge[0] == 1  &&  alice.areDisjoint(edge[1], edge[2])) {
                alice.union(edge[1], edge[2]);
                usedEdges++;
            }
            else if (edge[0] == 2  &&  bob.areDisjoint(edge[1], edge[2])) {
                bob.union(edge[1], edge[2]);
                usedEdges++;
            }
        }
        if (alice.isConnected() && bob.isConnected())
            return edges.length - usedEdges;
        return -1;
    }


    /******************************************* Disjoint Set *************************************/
    static class DisjointSet {
        int[] parent;
        int[] size;
        int components;

        public DisjointSet(int n) {
            components = n;
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int parentOf(int u) {
            if (u == parent[u])
                return u;
            return parent[u] = parentOf(parent[u]);
        }

        public void union(int u, int v) {
            int parentU = parentOf(u);
            int parentV = parentOf(v);
            if (parentU == parentV)
                return;
            if (size[parentU] > size[parentV]) {
                parent[parentV] = parentU;
                size[parentU] += size[parentV];
            } else {
                parent[parentU] = parentV;
                size[parentV] += size[parentU];
            }
            components--;
        }

        public boolean areDisjoint(int u, int v) {
            return parentOf(u) != parentOf(v);
        }

        public boolean isConnected(){
            return components == 1;
        }
    }
}
