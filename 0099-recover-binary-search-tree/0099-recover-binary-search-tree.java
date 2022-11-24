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
    public void inorder(TreeNode root, TreeNode[] prev, TreeNode[] swapped){
        if (root == null)
            return;
        
        inorder(root.left, prev, swapped);
        
        if (prev[0] != null && prev[0].val > root.val){
            if (swapped[0] == null){
                swapped[0] = prev[0];
                swapped[1] = root;
            }
            else
                swapped[1] = root;
        }
        
        prev[0] = root;
        inorder(root.right, prev, swapped);
    }
    
    public void recoverTree(TreeNode root) {
        TreeNode[] swapped = new TreeNode[]{null, null};
        
        inorder(root, new TreeNode[]{null}, swapped);
        
        int temp = swapped[0].val;
        swapped[0].val = swapped[1].val;
        swapped[1].val = temp;
    }
}