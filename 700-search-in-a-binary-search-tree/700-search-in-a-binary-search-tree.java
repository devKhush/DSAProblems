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
    public TreeNode searchBST(TreeNode root, int target) {
        if (root == null)
            return null;
        
        if (root.val == target)
            return root;
        
        else if (root.val > target)
            return searchBST(root.left, target);
        
        else if (root.val < target)
            return searchBST(root.right, target);
        
        return null;
    }
}