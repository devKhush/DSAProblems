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
    private boolean pathSum(TreeNode root, int targetSum, int sum){
        if (root == null)
            return false;
        
        if (root.left == null && root.right == null)
            return root.val + sum == targetSum;
        
        return pathSum(root.left, targetSum, sum + root.val) || pathSum(root.right, targetSum, sum + root.val);
    }
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return this.pathSum(root, targetSum, 0);
    }
}