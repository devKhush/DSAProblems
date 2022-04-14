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
    
    public List<TreeNode> allPossibleFBT(int n) {
        ArrayList<TreeNode> answer = new ArrayList<>();
        
        if (n==1)
            answer.add(new TreeNode());
        
        else{
            for (int i=1; i<n; i+=2){
                List<TreeNode> leftTrees = allPossibleFBT(i);          
                List<TreeNode> rightTrees = allPossibleFBT(n - i -1);
    
                for (TreeNode leftTree : leftTrees){
                    for (TreeNode rightTree : rightTrees){
                        TreeNode root = new TreeNode(0, leftTree, rightTree); 
                        answer.add(root);
                    }
                }
            }
        }
        return answer;
    }
    
}