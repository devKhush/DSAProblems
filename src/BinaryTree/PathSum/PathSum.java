package BinaryTree.PathSum;

class PathSum {
    // Simple logic as in DP
    // Time Complexity: O(n)        We will traverse the entire Tree
    // Space Complexity: O(log(n))  Recursion stack space of Height of the Tree

    public boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case
        if (root == null)
            return false;

        // Check for Leaf Node
        if (root.left == null && root.right == null)
            return targetSum == root.val;

        // Decrease target by current node value
        targetSum -= root.val;

        // Explore Path in Left & Right SubTree
        return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
    }


    // ******************************* Tree Node for Tree Data Structure ******************************
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }
}