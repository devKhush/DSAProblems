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
    
    public boolean isValidBST(TreeNode root) {
          return checkSorted_inorder(root, new long[]{Long.MIN_VALUE});
    }
}