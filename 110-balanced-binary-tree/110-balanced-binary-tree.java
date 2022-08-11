/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        int leftSubTreeHeight = height(root.left);
        int rightSubTreeHeight = height(root.right);

        return isBalanced(root.left) && isBalanced(root.right) && Math.abs(leftSubTreeHeight - rightSubTreeHeight) <= 1;
    }

    // Standard Height function for a Binary Tree
    public int height(TreeNode root){
        if (root == null)
            return 0;

        return 1 + Math.max(height(root.left), height(root.right));
    }
}