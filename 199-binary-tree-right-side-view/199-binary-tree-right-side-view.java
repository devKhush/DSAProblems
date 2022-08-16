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
 *          this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    // BFS Solution 1 *************************************************************************
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> rightSideView = new ArrayList<>();
        if (root == null)
            return rightSideView;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){            
            int size = queue.size();
            
            for (int i = 1; i <= size; i++){
                TreeNode node = queue.remove();
                
                if (i == size)
                    rightSideView.add(node.val);
                
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return rightSideView;
    }   
}