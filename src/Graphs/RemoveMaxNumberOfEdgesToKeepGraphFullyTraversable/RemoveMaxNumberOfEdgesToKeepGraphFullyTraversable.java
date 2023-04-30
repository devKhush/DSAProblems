package Graphs.RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable;

// https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/description/

public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {
    /***************************************** Solution 1 *********************************************
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
        int deleteEdges = 0;

        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (alice.areDisjoint(edge[1], edge[2]) || bob.areDisjoint(edge[1], edge[2])) {
                    alice.union(edge[1], edge[2]);
                    bob.union(edge[1], edge[2]);
                } else deleteEdges++;
            }
        }
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (alice.areDisjoint(edge[1], edge[2]))
                    alice.union(edge[1], edge[2]);
                else
                    deleteEdges++;
            } else if (edge[0] == 2) {
                if (bob.areDisjoint(edge[1], edge[2]))
                    bob.union(edge[1], edge[2]);
                else
                    deleteEdges++;
            }
        }

        boolean aliceCanTravel = false, bobCanTravel = false;
        for (int u = 1; u <= n; u++) {
            aliceCanTravel |= (alice.size[u] == n);
            bobCanTravel |= (bob.size[u] == n);
        }
        if (!aliceCanTravel || !bobCanTravel)
            return -1;
        return deleteEdges;
    }


    /******************************************* Disjoint Set *************************************/
    static class DisjointSet {
        int[] parent;
        int[] size;

        public DisjointSet(int n) {
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
        }

        public boolean areDisjoint(int u, int v) {
            return parentOf(u) != parentOf(v);
        }
    }

}