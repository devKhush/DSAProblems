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
     private TreeNode insertBST(TreeNode root, int value){
        if (root == null)
            return new TreeNode(value);

        if (value < root.val)
            root.left = insertBST(root.left, value);
        if (root.val < value)
            root.right = insertBST(root.right, value);
        return root;
    }
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = null;
        for (int node : preorder)
            root = insertBST(root, node);

        return root;   
    }
}