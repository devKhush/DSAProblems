package Graphs.AccountsMerge;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;

// https://youtu.be/FMwpt_aQOGw
// https://takeuforward.org/data-structure/accounts-merge-dsu-g-50/
// https://leetcode.com/problems/accounts-merge/description/

public class AccountsMerge {
    /************************************ Disjoint-Set Solution **************************************
     * Intuition: Use a Disjoint set to keep track of Merged Email accounts

     * Time Complexity: O(n * m * log(m))
        * where N is the size of accounts and M is the size of number of strings for a name.
        *  O(mn) for adding emails to HashMap
        *  O(mn) again for adding merged emails to list
        *  O(mn * log(m)) to generate the final merged account list

     *  Space Complexity: O(n) + O(n*m)
        * O(n) for Disjoint-Set
        * O(mn) for HashMap and merged ArrayList
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);

        HashMap<String, Integer> emailToVal = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (emailToVal.containsKey(email)) {
                    ds.union(i, emailToVal.get(email));
                } else {
                    emailToVal.put(email, i);
                }
            }
        }

        List<List<String>> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<>());
        }
        for (String email : emailToVal.keySet()) {
            int parent = ds.getParent(emailToVal.get(email));
            arr.get(parent).add(email);
        }

        List<List<String>> mergedAccounts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr.get(i).isEmpty())
                continue;
            List<String> list = arr.get(i);
            Collections.sort(list);
            list.add(0, accounts.get(i).get(0));
            mergedAccounts.add(list);
        }
        return mergedAccounts;
    }
}



/*********************************** Disjoint-Set Data Structure: Union By size **************************/
class DisjointSet {
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
