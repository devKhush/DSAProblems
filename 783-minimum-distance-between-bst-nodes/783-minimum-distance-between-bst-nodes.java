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
    ArrayList<Integer> inOrder = new ArrayList<>();
    
    public void inOrder(TreeNode root){
        if (root!=null){
            inOrder(root.left);
            inOrder.add(root.val);
            inOrder(root.right);
        }
    }
    
    public int minDiffInBST(TreeNode root) {
        this.inOrder(root);
        int minDiff = Integer.MAX_VALUE;
        
        for (int i=0; i<inOrder.size(); i++){
            if (i != inOrder.size()-1){
                int currDiff = inOrder.get(i+1)-inOrder.get(i);
                minDiff = Math.min(minDiff, currDiff); 
            }
        }
        return minDiff;
    }
}