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
    // DFS Soltution *************************************************************************
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> rightSideView = new ArrayList<>();

        dfs_RightSideView(root, 0, rightSideView);        
        return rightSideView;
    }

    public void dfs_RightSideView(TreeNode node, int level, ArrayList<Integer> rightSideView){
        if (node == null)
            return;

        if (level == rightSideView.size())
            rightSideView.add(node.val);
        
        dfs_RightSideView(node.right, level + 1, rightSideView);
        dfs_RightSideView(node.left, level + 1, rightSideView);
    }
    
    
    // BFS Solution *************************************************************************
    public List<Integer> rightSideView_BFS(TreeNode root) {
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