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
    public boolean isUnivalTree(TreeNode root) {
        if (root == null)
            return true;
    
        return isUnivalTree_DFS(root, root.val);
    }
    
    public boolean isUnivalTree_DFS(TreeNode node, int value) {
        if (node == null)
            return true;
        
        if (node.val != value)
            return false;
        
        return isUnivalTree_DFS(node.left, value) && isUnivalTree_DFS(node.right, value);
    }
    
    
    public boolean isUnivalTree_BFS(TreeNode root) {
        if (root == null)
            return true;
        
        int value = root.val;
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            
            if (node.val != value){
                return false;
            }
            
            if (node.left != null)
                queue.add(node.left);
            
            if (node.right != null)
                queue.add(node.right);
        }
        return true;
    }
}