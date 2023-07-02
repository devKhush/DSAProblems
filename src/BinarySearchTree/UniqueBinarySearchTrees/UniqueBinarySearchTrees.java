package BinarySearchTree.UniqueBinarySearchTrees;

// DP on Trees 🔥🔥🔥🔥🔥
// https://leetcode.com/problems/unique-binary-search-trees/description/

public class UniqueBinarySearchTrees {
    /*************************************** Recursion **************************************
     * Intuition:
        * Basic Recursion thinking
        * Total no. of BST ==> Try out all the ways ==> Recursion
        * For every value of (1 to n), choose the current value say 'i' as the head.
        * Now, we need to find the number of BST can be made in left subtree with (i-1) nodes and right
            subtree with (n-i) nodes.
        * Suppose, if left & right subtree has 1 & 2 nodes respectively, so, the total no. of BST are 2.

     * Time Complexity: O(n * n^n)
        * there is 1 state of size n
        * In each call a loop if size n, and almost n recursive calls.
     * Space Complexity: O(n)
        * Recursion Stack Space
     * */
    public int numTrees_rec(int n) {
        if (n == 0) return 0;
        return f(n);
    }
    private int f(int n){
        if (n <= 1)
            return 1;
        int bst = 0;
        for (int i = 1; i <= n; i++){
            bst += f(i-1) * f(n-i);
        }
        return bst;
    }

    /********************************* Another Intuitive Reursion ***********************************
     * But don't use this for DP, this will have two states
     * This solution is improved in above recursion, bcoz we only care about the count of BSTs and
        not about the values in the BST.
     */
    public int numTrees_rec2(int n) {
        if (n == 0) return 0;
        return f(1, n);
    }
    public int f(int low, int high){
        if (low >= high)
            return 1;
        int bst = 0;
        for (int i = low; i <= high; i++){
            bst += f(low, i - 1) * f(i + 1, high);
        }
        return bst;
    }

    /************************************* Memoization ******************************************
     * Time Complexity: O(n*n)
        * One DP state and one loop
     * Space Complexity: O(n)
        * DP Array + Stack Space
     */
    public int numTrees_memo(int n) {
        if (n == 0) return 0;
        Integer[] dp = new Integer[n + 1];
        return f(n, dp);
    }
    private int f(int n, Integer[] dp){
        if (n <= 1)
            return 1;
        if (dp[n] != null)
            return dp[n];

        int bst = 0;
        for (int i = 1; i <= n; i++){
            bst += f(i - 1, dp) * f(n - i, dp);
        }
        return dp[n] = bst;
    }

    /******************************* Tabulation *******************************************
     * Time Complexity: O(n*(n+1)/2) ~ O(n*n)
        * One loop of size n in loop of size n
     * Space Complexity: O(n)
        * DP Array
     */
    public int numTrees(int N) {
        if (N == 0) return 0;
        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 1;

        for (int n = 2; n <= N; n++){
            int bst = 0;
            for (int i = 1; i <= n; i++){
                bst += dp[i - 1] * dp[n - i];
            }
            dp[n] = bst;
        }
        return dp[N];
    }
}
