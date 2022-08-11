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
    public int maxDepth(TreeNode root) {
        /* // Recursive Solution
        if (root == null)
            return 0;
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
        */
        
        if (root == null)
            return 0;

        Queue<TreeNode> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(root);
        int heightOfTree = 0;

        while (!bfsQueue.isEmpty()){
            int size = bfsQueue.size();
            heightOfTree++;
            
            for (int i = 0; i < size; i++){
                TreeNode node = bfsQueue.remove();
                
                if (node.left != null)
                    bfsQueue.add(node.left);
                if (node.right != null)
                    bfsQueue.add(node.right);
            }
        }
        return heightOfTree;
    }
}