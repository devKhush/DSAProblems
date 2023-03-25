package Graphs.CountUnreachablePairsOfNodesInAnUndirectedGraph;
import java.util.ArrayList;

// https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/description/

public class CountUnreachablePairsOfNodes_DisjointSet {
    /************************************ Disjoint Set Solution ******************************************8
     * Intuition:
        * We need to find the total number of nodes present in all the components of Graph and store it.
        * Finding total no. of nodes in a Graph can be done by Disjoint Set "Union by Size", since there is
            a size count for each parent node.
        * To optimize the pair finding code, we can optimize the O(n^2) loop to O(n) using a suffix array.

        * Time Complexity: O(E) + O(2*V)  ~  O(V + E)
            * O(E) for union of edges in Disjoint Set
            * O(V) to find parents
            * Another O(V) to find the pairs
        * Space Complexity: O(V)
            * Disjoint_set, Parents_list, Suffix_sum_array
     */
    public long countPairs(int V, int[][] edges) {
        DisjointSet ds = new DisjointSet(V);

        // Connect all the component in the graphs
        for (int[] edge : edges){
            ds.union(edge[0], edge[1]);
        }

        // Figure out tll the Parent nodes in Graphs
        ArrayList<Integer> parents = new ArrayList<>();
        long pairs = 0;
        for (int u = 0; u < V; u++){
            if (ds.parent[u] == u)
                parents.add(u);
        }

        // Find the size of all parents nodes in graph's components
        // we can optimize the O(n^2) loop to O(n) using a suffix array
        // A * B  +  A * C  =  A * (B + C)
        int numParents = parents.size();
        long[] suffixSum = new long[numParents];
        suffixSum[numParents - 1] = ds.size[parents.get(numParents - 1)];

        for (int i = numParents - 2; i >= 0; i--){
            suffixSum[i] = suffixSum[i + 1] + ds.size[parents.get(i)];
            pairs += ds.size[parents.get(i)] * suffixSum[i + 1];
        }
        return pairs;
    }


    static class DisjointSet{
        int[] parent;
        long[] size;
        public DisjointSet(int V){
            this.parent = new int[V];
            this.size = new long[V];
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

            if (size[uPar] > size[vPar]){
                parent[vPar] = uPar;
                size[uPar] += size[vPar];
            }
            else{
                parent[uPar] = vPar;
                size[vPar] += size[uPar];
            }
        }

        public boolean areDisjoint(int u, int v){
            return getParent(u) != getParent(v);
        }
    }
}
