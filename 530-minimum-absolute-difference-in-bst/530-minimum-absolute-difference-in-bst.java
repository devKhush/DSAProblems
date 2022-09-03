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
    public int getMinimumDifference(TreeNode root) {
        int[] minDiff = {Integer.MAX_VALUE};
        TreeNode[] prev = {null};
        
        minimumDifference(root, minDiff, prev);
        
        return minDiff[0];
    }
    
    public void minimumDifference(TreeNode node, int[] minDiff, TreeNode[] prev){
        if (node == null)
            return;
        
        minimumDifference(node.left, minDiff, prev);
        
        if (prev[0] != null)
            minDiff[0] = Math.min(minDiff[0], node.val - prev[0].val);
        
        prev[0] = node;
        
        minimumDifference(node.right, minDiff, prev);
    }
}