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
    // Efficient Solution ********************************************************************
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;   
    }

    public int height(TreeNode root){
        if (root == null)
            return 0;

        int leftHeight = height(root.left);
        if (leftHeight == -1)
            return -1;
        
        int rightHeight = height(root.right);
        if (rightHeight == -1)
            return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    
    
    // Brute Force Solution *****************************************************
    public boolean isBalancedTree(TreeNode root) {
        if (root == null)
            return true;

        int leftSubTreeHeight = height(root.left);
        int rightSubTreeHeight = height(root.right);

        return isBalancedTree(root.left) && isBalancedTree(root.right) && Math.abs(leftSubTreeHeight - rightSubTreeHeight) <= 1;
    }

    // Standard Height function for a Binary Tree
    public int getHeight(TreeNode root){
        if (root == null)
            return 0;

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}