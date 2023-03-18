package Graphs.DisjointSet.UnionBySize;

// Topic :  Disjoint Set || Union By Size and Path Compression
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

    // If 'i' is a representative of a set, size[i] is the number of nodes contained in the
    // set containing 'i'.
    private final int[] size;

    public DisjointSet(int V){
        this.parent = new int[V];
        this.size = new int[V];
        for (int i = 0; i < V; i++){
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Parent/Representative of the set can be found by the parent array
    private int getParent(int u){
        if (u == parent[u])
            return u;
        return parent[u] = getParent(parent[u]);
    }

    // Unites the set that includes 'u' and the set that includes 'v'
    private void union(int u, int v){
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

    // Two elements 'u' & 'v' will don't belong to the same set
    public boolean areDisjoint(int u, int v){
        return getParent(u) != getParent(v);
    }



    // Testing
    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(8);
        ds.union(1, 2);
        ds.union(3, 2);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        System.out.println(!ds.areDisjoint(1, 5) ? "Joint" : "Disjoint");
        ds.union(3, 7);
        System.out.println(!ds.areDisjoint(1, 5) ? "Joint" : "Disjoint");
    }
}
