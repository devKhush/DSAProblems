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
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        
        List<List<Integer>> levelOrderTraversal = new ArrayList<>();
        
        while (!queue.isEmpty()){
            int size = queue.size();
            
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < size; i++){
                TreeNode treenode = queue.remove();
                currentLevel.add(treenode.val);
                
                if (treenode.left != null)
                    queue.add(treenode.left);
                if (treenode.right != null)
                    queue.add(treenode.right);
            }
            levelOrderTraversal.add(currentLevel);
        }
        return levelOrderTraversal;
    }
}