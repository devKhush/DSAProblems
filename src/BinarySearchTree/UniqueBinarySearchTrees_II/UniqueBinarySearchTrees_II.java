package BinarySearchTree.UniqueBinarySearchTrees_II;
import java.util.*;

// Pre-requisite: "Unique Binary Search Trees"
// https://leetcode.com/problems/unique-binary-search-trees-ii/description/
// https://leetcode.com/problems/unique-binary-search-trees-ii/editorial/

public class UniqueBinarySearchTrees_II {
    /************************************ Recursion *******************************************
     * Intuition:
        * Basic recursion thinking
     * Time Complexity: O(n * (n*n)^n) ~ exponential
     * Space Complexity: O(n)
        * stack space
     */
    public List<TreeNode> generateTrees_rec(int n) {
        return generateTrees(1, n);
    }
    private List<TreeNode> generateTrees(int low, int high){
        List<TreeNode> bst = new ArrayList<>();
        if (low > high){
            bst.add(null);
            return bst;
        }

        for (int i = low; i <= high; i++){
            List<TreeNode> leftSubTree = generateTrees(low, i - 1);
            List<TreeNode> rightSubTree = generateTrees(i + 1, high);
            for (TreeNode left : leftSubTree){
                for (TreeNode right : rightSubTree){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    bst.add(root);
                }
            }
        }
        return bst;
    }


    /******************************************* Memoization *************************************
     * Time Complexity: O(n*n*n)
        * Two DP states & one loop for each call
     * Space Complexity: O(n*n*size) + O(n)
        * O(n*n*size): DP Array of size n*n, and size is the average length of BST list with range from [low,high]
        * O(n) for stack space
     */
    public List<TreeNode> generateTrees_memo(int n) {
        List<TreeNode>[][] dp = new List[n + 1][n + 1];
        return generateTrees(1, n, dp);
    }
    private List<TreeNode> generateTrees(int low, int high, List<TreeNode>[][] dp){
        List<TreeNode> bst = new ArrayList<>();
        if (low > high){
            bst.add(null);
            return bst;
        }
        if (dp[low][high] != null)
            return dp[low][high];

        for (int i = low; i <= high; i++){
            List<TreeNode> leftSubTree = generateTrees(low, i - 1, dp);
            List<TreeNode> rightSubTree = generateTrees(i + 1, high, dp);
            for (TreeNode left : leftSubTree){
                for (TreeNode right : rightSubTree){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    bst.add(root);
                }
            }
        }
        return dp[low][high] = bst;
    }

    /***************************************** Tabulation *****************************************
     * Time Complexity: O(n*n*n)
        * Two loops for DP states
        * one loop inside each DP state of low & high

     * Space Complexity: O(n*n*size)
        * O(n*n*size): DP Array of size n*n, and size is the average length of BST list with range from [low,high]
     */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[][] dp = new List[n + 2][n + 2];

        // Base case for "low > high"
        for (int low = 0; low <= n+1; low++){
            for (int high = 0; high < low; high++){
                dp[low][high] = new ArrayList<>();
                dp[low][high].add(null);
            }
        }

        // Remaining DP states written in reverse order
        for (int low = n; low >= 1; low--){
            for (int high = low; high <= n; high++){

                List<TreeNode> bst = new ArrayList<>();
                for (int i = low; i <= high; i++){
                    List<TreeNode> leftSubTree = dp[low][i - 1];
                    List<TreeNode> rightSubTree = dp[i + 1][high];
                    for (TreeNode left : leftSubTree){
                        for (TreeNode right : rightSubTree){
                            TreeNode root = new TreeNode(i);
                            root.left = left;
                            root.right = right;
                            bst.add(root);
                        }
                    }
                }
                dp[low][high] = bst;
            }
        }
        return dp[1][n];
    }

    static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
