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
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        
        return checkSymmetric(root.left, root.right);
    }
    
    public boolean checkSymmetric(TreeNode A, TreeNode B){
        if (A == null && B == null)
            return true;
        
        if (A == null || B == null)
            return false;
        
        return (A.val == B.val)  &&  checkSymmetric(A.right, B.left)  &&  checkSymmetric(A.left, B.right);   
    }
}