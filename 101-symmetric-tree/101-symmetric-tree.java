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
    public boolean isSymmetric(TreeNode root) {
        // return checkSymmetric(root.left, root.right);        
        
         if (root == null)
            return true;

        if (root.left == null  &&  root.right == null)
            return true;

        if (root.left == null  || root.right == null)
            return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()){
            TreeNode node1 = queue.remove();
            TreeNode node2 = queue.remove();

            if (node1 == null && node2 == null)
                continue;

            if (node1 == null || node2 == null)
                return false;

            if (node1.val != node2.val)
                return false;
            
            queue.add(node1.right);
            queue.add(node2.left);
            queue.add(node1.left);
            queue.add(node2.right);
        }
        return true;
    }
    
    
    public boolean checkSymmetric(TreeNode A, TreeNode B){
        if (A == null && B == null)
            return true;
        
        if (A == null || B == null)
            return false;
        
        return (A.val == B.val)  &&  checkSymmetric(A.right, B.left)  &&  checkSymmetric(A.left, B.right);   
    }
}