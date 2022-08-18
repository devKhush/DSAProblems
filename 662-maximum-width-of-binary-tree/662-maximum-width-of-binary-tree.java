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
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        
        int maxWidth = 0;
        
        Queue<NodePair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.add(new NodePair<>(root, 0));
        
        while (!queue.isEmpty()){
            int size = queue.size();
            int firstIndex = 0, lastIndex = 0;
            int minIndex = queue.peek().index;
            
            for (int i = 0; i < size; i++){
                NodePair<TreeNode, Integer> node = queue.remove();
                
                if (i == 0)
                    firstIndex = node.index;
                if (i == size - 1)
                    lastIndex = node.index;
                
                if (node.treeNode.left != null)
                    queue.add(new NodePair<>(node.treeNode.left, 2 * (node.index - minIndex) + 1));
                
                if (node.treeNode.right != null)
                    queue.add(new NodePair<>(node.treeNode.right, 2 * (node.index - minIndex) + 2));
            }
            maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
        }
        return maxWidth;
    }
    
    
    private static class NodePair<A, B>{
        A treeNode;
        B index;
        public NodePair(A treeNode, B index){
            this.treeNode = treeNode;
            this.index = index;
        }
    }
}