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
    
    List<Integer> inOrderTraversal = new ArrayList<Integer>();
    
    public void inOrder(TreeNode root) {
        if (root!=null){
            inOrder(root.left);
            inOrderTraversal.add(root.val);
            inOrder(root.right);
        }    
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        inOrder(root);
        return inOrderTraversal;                
    }
}