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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        
        List<List<Integer>> levelOrder = new ArrayList<>();

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            levelOrder.add(currentLevel);

            for (int i = 0; i < size; i++){
                TreeNode node = queue.remove();
                currentLevel.add(node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        
        int n = levelOrder.size();
        for (int i = 0; i < n / 2; i++){
            List<Integer> list = levelOrder.get(i);
            levelOrder.set(i, levelOrder.get(n - 1 - i));
            levelOrder.set(n - 1 - i, list);
        }
        return levelOrder;
    }
}