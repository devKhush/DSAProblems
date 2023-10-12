package Graphs.MinCostToConnectAllPoints;
import java.util.ArrayList;

// https://leetcode.com/problems/min-cost-to-connect-all-points/description/

public class MinCostToConnectAllPoints {
    /*********************************** Kruskal Algorithm ****************************************
     * Time Complexity: O(E*log(E))
        * E -> no. of edges = n^2
     * Space Complexity: O(V + E)
        * V for DSU
        * E for edges
     */
    public int minCostConnectPoints(int[][] points) {
        int V = points.length;
        ArrayList<int[]> edges = new ArrayList<>();
        for (int u = 0; u < V; u++){
            for (int v = u + 1; v < V; v++){
                edges.add(new int[]{u, v, Math.abs(points[u][0] - points[v][0]) + Math.abs(points[u][1] - points[v][1])});
            }
        }
        edges.sort((a, b) -> a[2] - b[2]);

        DisjointSet set = new DisjointSet(V);
        int mstCost = 0;
        for (int[] edge: edges){
            if (set.areDisjoint(edge[0], edge[1])){
                mstCost += edge[2];
                set.union(edge[0], edge[1]);
            }
            if (set.size(edge[0]) == V || set.size(edge[1]) == V)
                return mstCost;
        }
        return 0;
    }

    static class DisjointSet{
        int[] parent;
        int[] size;
        int V;
        public DisjointSet(int V){
            this.V = V;
            this.parent = new int[V];
            this.size = new int[V];
            for (int u = 0; u < V; u++){
                size[u] = 1;
                parent[u] = u;
            }
        }
        public int parent(int u){
            if (u == parent[u])
                return u;
            return parent[u] = parent(parent[u]);
        }
        public int size(int u){
            return size[u];
        }
        public void union(int u, int v){
            int parU = parent(u);
            int parV = parent(v);
            if (parU == parV) return;
            if (size[parU] >= size[parV]){
                parent[parV] = parU;
                size[parU] += size[parV];
            }
            else{
                parent[parU] = parV;
                size[parV] += size[parU];
            }
        }
        public boolean areDisjoint(int u, int v){
            return parent(u) != parent(v);
        }
    }
}
