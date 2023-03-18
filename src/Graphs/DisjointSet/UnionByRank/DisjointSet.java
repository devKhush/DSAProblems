package Graphs.DisjointSet.UnionByRank;

// Topic :  Disjoint Set || Union By Rank and Path Compression
// https://youtu.be/3gbO7FDYNFQ
// https://youtu.be/aBxjDBC4M1U (new for in-depth intuition)
// https://takeuforward.org/data-structure/disjoint-set-union-by-rank-union-by-size-path-compression-g-46/
// Great Reading (MUST): https://www.geeksforgeeks.org/disjoint-set-data-structures/

/**
 * Time Complexity  : O(4*a) = O(4) = O(1)    Amortized O(1) time complexity
 * Space Complexity : O(n)      for n elements
 */

public class DisjointSet {
    // Stores the parent/representative of the set
    private final int[] parent;

    // If 'i' is a representative of a set, rank[i] is the height of the tree representing the set.
    private final int[] rank;

    public DisjointSet(int V){
        this.parent = new int[V];
        this.rank = new int[V];

        // the i’th element of the parent array will be the parent of the i’th item
        // Initially, all elements are in their own set (parent of their own)
        for (int i = 0; i < V; i++)
            parent[i] = i;
    }


    // Returns representative of set containing 'u'
    // Parent/Representative of the set can be found by recursively traversing the parent array
    // until we hit an element who is parent/representative of itself
    public int parentOf(int u){
        // If 'i' is the parent of itself, then 'i' is the representative of this set
        if (u == parent[u])
            return u;

        // If 'i' is not the parent/representative. So we recursively call parentOf() on its parent
        return parent[u] = parentOf(parent[u]);
    }


    // Unites the set that includes 'u' and the set that includes 'v'
    public void union(int u, int v){
        int u_parent = parentOf(u);
        int v_parent = parentOf(v);

        // Elements are in same set, no need to unite anything
        if (u_parent == v_parent)
            return;

        // If x's rank is less than y's rank, then move x under y  so that depth of tree remains less
        if (rank[u_parent] > rank[v_parent]) {
            parent[v_parent] = u_parent;
        }
        // Else if y's rank is less than x's rank, then move y under x so that depth of tree remains less
        else if (rank[v_parent] > rank[u_parent]) {
            parent[u_parent] = v_parent;
        }
        // If ranks are the same, move anyone under the other
        else if (rank[u_parent] == rank[v_parent]){
            // We can also make either "v as parent of u"
            parent[u_parent] = v_parent;
            rank[v_parent]++;

            /*
            // We can also make either "u as parent of v"
            // Instead of above, we can also do this
             parent[v_parent] = u_parent;
             rank[u_parent]++;
            */
        }
    }

    // Two elements 'u' & 'v' will belong to the same set, iff their representatives are same
    public boolean belongsToSameSet(int u, int v){
        return parentOf(u) == parentOf(v);
    }
}
