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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        // return getLeftLeafNodeSum(root.left, root) + getLeftLeafNodeSum(root.right, root);
        return getLeftLeafNodeSum_(root.left, true) + getLeftLeafNodeSum_(root.right, false);
    }
    
    private int getLeftLeafNodeSum_(TreeNode root, boolean isLeftTree){
        if (root == null)
            return 0;
        
        if (root.left == null  && root.right == null){
            if (isLeftTree)
                return root.val;
            else
                return 0;
        }
        return getLeftLeafNodeSum_(root.left, true) + getLeftLeafNodeSum_(root.right, false);
    }
    
    
    private int getLeftLeafNodeSum(TreeNode root, TreeNode parent){
        if (root == null)
            return 0;
        
        if (root.left == null  && root.right == null){
            if (root == parent.left)
                return root.val;
            else
                return 0;
        }
        return getLeftLeafNodeSum(root.left, root) + getLeftLeafNodeSum(root.right, root);
    }
    
}