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
    public TreeNode inOrder(TreeNode node, int[] k){
        if (node == null)
            return null;
        
        TreeNode left = inOrder(node.left, k);
        if (left != null)
            return left;
        
        k[0]--;
        
        if (k[0] == 0)
            return node;
        
        TreeNode right = inOrder(node.right, k);
        if (right != null)
            return right;
        
        return null;
    }
    
    public int kthSmallest(TreeNode root, int K) {
        int[] k = {K};

        TreeNode kthSmallestNode = inOrder(root, k);        
        return kthSmallestNode.val;
    }
}