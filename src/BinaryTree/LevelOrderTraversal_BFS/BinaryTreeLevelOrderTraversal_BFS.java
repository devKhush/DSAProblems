package BinaryTree.LevelOrderTraversal_BFS;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// PRE_REQUISITE: "ROTTEN ORANGES" in QUEUES

class BinaryTreeLevelOrderTraversal_BFS {
    /* Level Order traversal is simply BFS (Breadth First Search) of Tree
    * Time Complexity: O(n)
    * Space Complexity: O(n)        Due to queue  (though much less than O(n))
     */
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


    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int val) { this.val = val; }
  }
}