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
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> rightSideView = new ArrayList<>();
        if (root == null)
            return rightSideView;
        
        HashMap<Integer, Integer> rightSideMap = new HashMap<>();
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(root, 0));
        
        while (!queue.isEmpty()){
            Node node = queue.remove();
            
            rightSideMap.put(node.level, node.treeNode.val);
            
            if (node.treeNode.left != null)
                queue.add(new Node(node.treeNode.left, node.level + 1));
            
            if (node.treeNode.right != null)
                queue.add(new Node(node.treeNode.right, node.level + 1));
        }
        
        int level = 0;
        while (rightSideMap.containsKey(level)){
            rightSideView.add(rightSideMap.get(level));
            level++;
        }
        return rightSideView;
    }
    
    
    private static class Node{
        TreeNode treeNode;
        int level;
        public Node(TreeNode treeNode, int level){
            this.treeNode = treeNode;
            this.level = level;
        } 
    }
}