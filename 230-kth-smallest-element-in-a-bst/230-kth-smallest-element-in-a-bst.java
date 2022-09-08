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
    public boolean inOrder(TreeNode node, int[] answer){
        if (node == null)
            return false;
        
        if (inOrder(node.left, answer))
            return true;
        
        answer[0]--;
        if (answer[0] == 0){
            answer[1] = node.val;
            return true;
        }
        
        if (inOrder(node.right, answer))
            return true;
        
        return false;
    }
    
    public int kthSmallest(TreeNode root, int k) {
        int[] kSmallest = {k, 0};

        inOrder(root, kSmallest);        
        return kSmallest[1];
    }
}