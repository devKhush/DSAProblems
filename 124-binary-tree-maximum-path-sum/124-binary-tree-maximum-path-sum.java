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
    public int maxPathSum(TreeNode root) {
        int[] maxPathSum = {Integer.MIN_VALUE};
        
        findMaxPathSum(root, maxPathSum);
        
        return maxPathSum[0];
    }
    
    public int findMaxPathSum(TreeNode root, int[] maxPathSum){
        if (root == null)
            return 0;
        
        int leftMaxPathSum = Math.max(0, findMaxPathSum(root.left, maxPathSum));
        int rightMaxPathSum = Math.max(0, findMaxPathSum(root.right, maxPathSum));
        
        maxPathSum[0] = Math.max(maxPathSum[0], leftMaxPathSum + root.val + rightMaxPathSum);
        
        return Math.max(leftMaxPathSum, rightMaxPathSum) + root.val;
    }
}