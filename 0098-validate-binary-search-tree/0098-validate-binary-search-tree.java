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
    // Morris ************************************************************
     public boolean isValidBST(TreeNode root){
        long value = Long.MIN_VALUE;
        TreeNode node = root;

        while (node != null){
            if (node.left == null){
                if (node.val <= value)
                    return false;
                value = node.val;
                node = node.right;
            }
            else{
                TreeNode ptr = node.left;
                while (ptr.right != null && ptr.right != node)
                    ptr = ptr.right;

                if (ptr.right == null){
                    ptr.right = node;
                    node = node.left;
                }
                else{
                    ptr.right = null;
                    if (node.val <= value)
                        return false;
                    value = node.val;
                    node = node.right;
                }
            }
        }
        return true;
    }
    
    
    // Recursive ********************************************************
    public boolean checkSorted_inorder(TreeNode root, long[] value){
        if (root == null)
            return true;
        
        if (checkSorted_inorder(root.left, value) == false)
            return false;
        
        if (value[0] >= root.val)
            return false;
        value[0] = root.val;
        
        if (checkSorted_inorder(root.right, value) == false)
            return false;
        
        return true;
    }
    public boolean isValidBST_Recursive(TreeNode root) {
        return checkSorted_inorder(root, new long[]{Long.MIN_VALUE});
    }
}