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
    public int kthSmallest(TreeNode root, int K) {
        // TreeNode kthSmallestNode = inOrder(root, new int[]{K});        
        // return kthSmallestNode.val;

        int count = 0;
        TreeNode curr = root;
        
        while (curr != null){
            if (curr.left == null){
                count++;
                if (count == K)
                    return curr.val;
                curr = curr.right;
            }
            else{
                TreeNode prev = curr.left;
                
                while (prev.right != null  &&  prev.right != curr)
                    prev = prev.right;
                
                if (prev.right == null){
                    prev.right = curr;
                    curr = curr.left;
                }
                else{
                    prev.right = null;
                    count++;
                    if (count == K)
                        return curr.val;
                    curr = curr.right;
                }
            }
        }
        return -1;
    }
    
    public TreeNode inOrder(TreeNode node, int[] k){
        if (node == null)
            return null;
        
        TreeNode left = inOrder(node.left, k);
        if (left != null)
            return left;
        
        k[0]--;
        if (k[0] == 0)
            return node;
        
        TreeNode right = inOrder(node.right, k);
        if (right != null)
            return right;
        
        return null;
    }
}