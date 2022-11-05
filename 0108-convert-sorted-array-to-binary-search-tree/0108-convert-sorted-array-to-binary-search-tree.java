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
    public TreeNode sortedArrayToBST(int[] inorder) {
        return constructBST(inorder, 0, inorder.length - 1);
    }
    private TreeNode constructBST(int[] inorder, int low, int high){
        if (low > high)
            return null;
        
        int mid = low + (high - low)/2;
        TreeNode root = new TreeNode(inorder[mid]);
        
        root.left = constructBST(inorder, low, mid - 1);
        root.right = constructBST(inorder, mid + 1, high);
        return root;
    }
}