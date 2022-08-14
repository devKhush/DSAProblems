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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
           List<List<Integer>> zigzagTraversal = new ArrayList<>();
        if (root == null)
            return zigzagTraversal;

        boolean addFromLeftToRight = true;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);

        while (!deque.isEmpty()){
            List<Integer> currLevel = new ArrayList<>();
            zigzagTraversal.add(currLevel);
            
            int size = deque.size();
            for (int i = 0; i < size; i++){
                
                if (addFromLeftToRight){
                    TreeNode node = deque.removeFirst();
                    currLevel.add(node.val);
                    
                    if (node.left != null)
                        deque.addLast(node.left);
                    if (node.right != null)
                        deque.addLast(node.right);
                }
                else{
                    TreeNode node = deque.removeLast();
                    currLevel.add(node.val);
                    
                    if (node.right != null)
                        deque.addFirst(node.right);
                    if (node.left != null)
                        deque.addFirst(node.left);
                }
            }
            addFromLeftToRight = !addFromLeftToRight;
        }
        return zigzagTraversal;
    }
    
    
    // Solution 1: Not preferred Though
    public List<List<Integer>> zigzagLevelOrder_V1(TreeNode root) {
        List<List<Integer>> zigzagTraversal = new ArrayList<>();
        if (root == null)
            return zigzagTraversal;

        boolean addNodeToLast = true;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currLevel = new ArrayList<>();
            zigzagTraversal.add(currLevel);

            while (size-- > 0){
                TreeNode node = queue.remove();

                if (addNodeToLast)
                    currLevel.add(node.val);
                else
                    currLevel.add(0, node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            addNodeToLast = !addNodeToLast;
        }
        return zigzagTraversal;
    }
}