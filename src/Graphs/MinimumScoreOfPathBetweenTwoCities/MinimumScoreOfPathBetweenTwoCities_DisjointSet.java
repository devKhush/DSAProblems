package Graphs.MinimumScoreOfPathBetweenTwoCities;

// https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/description/

// *********************************** Most Efficient Solution *************************************

public class MinimumScoreOfPathBetweenTwoCities_DisjointSet {
    /***********************************  Disjoint-Set Solution *****************************************
     * Intuition: Since we can visit a travelled path again, so we can always traverse path having minimum
        edge and come back to "node 1" again. Then, afterwards we can visit the "node n".
        * So, we just need to find min. edge cost present in the graph.

     *  Time Complexity: O(2*E)  ~  O(E)
     *  Space Complexity: O(V)
     */
    public int minScore(int n, int[][] roads) {
        DisjointSet ds = new DisjointSet(n + 1);

        // Merge all the components in the graph
        for (int[] edge : roads) {
            ds.union(edge[0], edge[1]);
        }
        // If '1' and 'n' are still disconnected, return INF as answer
        if (ds.areDisjoint(1, n))
            return Integer.MAX_VALUE;

        // If '1' and 'n' are connected, figure out the min. cost edge in component connected with '1' and 'n'
        int minEdge = Integer.MAX_VALUE;
        for (int[] edge : roads) {
            int u = edge[0], v = edge[1], cost = edge[2];
            if (!ds.areDisjoint(1, u)) {
                minEdge = Math.min(minEdge, cost);
            }
        }
        return minEdge;
    }



    // ************************************** Disjoint Set ******************************************
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
