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
    public TreeNode insertIntoBST(TreeNode root, int value) {
        if (root == null)
            return new TreeNode(value);
        
        TreeNode node = root;
        
        while (true){
            if (value > node.val){
                if (node.right == null){
                    node.right = new TreeNode(value);
                    break;
                }
                else
                    node = node.right;
            }
            else if (value < node.val){
                if (node.left == null){
                    node.left = new TreeNode(value);
                    break;
                }
                else
                    node = node.left;
            }
        }
        return root;
    }
}