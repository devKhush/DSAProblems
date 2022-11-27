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
    int maxBSTSum = 0;
    
    public int maxSumBST(TreeNode root) {
        findmaximumSumBST(root);
        return maxBSTSum;
    }
    
    public NodeValue findmaximumSumBST(TreeNode root) {
        if (root == null)
            return new NodeValue(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        
        NodeValue left = findmaximumSumBST(root.left);
        NodeValue right = findmaximumSumBST(root.right);
        
        if (left.maxNode < root.val && root.val < right.minNode){
            maxBSTSum = Math.max(maxBSTSum, left.sum + right.sum + root.val);
            return new NodeValue(left.sum + right.sum + root.val, Math.min(left.minNode, root.val), Math.max(right.maxNode, root.val));
        }
        
        return new NodeValue(Math.max(left.sum, right.sum), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private static class NodeValue{
        int sum;
        int maxNode, minNode;
        public NodeValue(int sum, int min, int max){
            this.sum = sum;
            this.minNode = min;
            this.maxNode = max;
        }
    }
}