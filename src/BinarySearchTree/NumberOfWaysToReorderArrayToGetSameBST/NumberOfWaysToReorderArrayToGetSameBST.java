package BinarySearchTree.NumberOfWaysToReorderArrayToGetSameBST;
import java.util.ArrayList;

// https://youtu.be/_ZcQLiyQ3yM
// https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/

public class NumberOfWaysToReorderArrayToGetSameBST {
    /***************************************** Efficient Solution ************************************
     * Intuition:
        * Recursion for getting total no. of ways
     * Time Complexity: O(n^2)
        * O(n^2) to compute combinations table
        * O(n^2) for DFS, O(n) loop for every node
     * Space Complexity: O(n^2) + O(n)
        * O(n^2) for combinations table
        * O(n) recursion stack space
     */
    int MOD = (int)(1e9 + 7);
    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Compute the C(n,r) using pascal triangle. Formula for "C(n,r) = C(n-1,r-1) + C(n-1,r)"
        // i loop for n, and j loop for r
        long[][] dp = new long[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= i; j++){
                if (j == 0 || j == i)
                    dp[i][j] = 1;
                else
                    dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % MOD;
            }
        }

        ArrayList<Integer> bstNodes = new ArrayList<>();
        for (int node : nums){
            bstNodes.add(node);
        }
        // -1 to the answer for current configuration
        return (int)dfs(bstNodes, dp) - 1;
    }

    // dfs(nodes) --> denotes no. of ways to reorder given set of nodes to get same BST
    public long dfs(ArrayList<Integer> nodes, long[][] combinations){
        int n = nodes.size();
        if (n <= 2)
            return 1;

        // Separate left and right BST nodes
        ArrayList<Integer> leftNodes = new ArrayList<>();
        ArrayList<Integer> rightNodes = new ArrayList<>();
        for (int i = 1; i < n; i++){
            if (nodes.get(i) < nodes.get(0))
                leftNodes.add(nodes.get(i));
            else
                rightNodes.add(nodes.get(i));
        }
        long leftOrderWays = dfs(leftNodes, combinations);
        long rightOrderWays = dfs(rightNodes, combinations);

        // No. of ways to reorder the BST = C(n-1, number_of_left_nodes) * ways_to_reorder_left_nodes * ways_to_reorder_right_nodes
        return (combinations[n - 1][leftNodes.size()] * ((leftOrderWays * rightOrderWays) % MOD)) % MOD;
    }
}
