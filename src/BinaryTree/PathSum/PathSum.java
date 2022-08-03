package BinaryTree.PathSum;

class PathSum {
    // Simple logic as in DP
    // Time Complexity: O(n)        We will traverse the entire Tree
    // Space Complexity: O(log(n))  Recursion stack space of Height of the Tree

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return this.pathSum(root, targetSum, 0);
    }

    private boolean pathSum(TreeNode root, int targetSum, int sum){
        if (root == null)
            return false;

        if (root.left == null && root.right == null)
            return root.val + sum == targetSum;

        boolean pathSumFoundOnLeft = pathSum(root.left, targetSum, sum + root.val);
        boolean pathSumFoundOnRight = pathSum(root.right, targetSum, sum + root.val);
        return pathSumFoundOnLeft || pathSumFoundOnRight;
    }


    // ******************************* Tree Node for Tree Data Structure ********************************
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}