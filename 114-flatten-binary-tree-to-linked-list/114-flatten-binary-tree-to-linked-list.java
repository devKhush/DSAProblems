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
    public void preOrder(TreeNode root, ArrayList<TreeNode> preorder){
        if (root!=null){
            preorder.add(root);
            preOrder(root.left, preorder);
            preOrder(root.right, preorder);
        }
    }
    
    public void flatten(TreeNode root) {
        ArrayList<TreeNode> preOrderTraversal = new ArrayList<>();
        preOrder(root, preOrderTraversal);
        
        for (int i = 0; i < preOrderTraversal.size() - 1; i++){
            TreeNode temp = preOrderTraversal.get(i);
            temp.left = null;
            temp.right = null;
            
            temp.right = preOrderTraversal.get(i + 1);
        }
    }
}