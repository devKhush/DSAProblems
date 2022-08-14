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
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> verticalOrderTraversal = new ArrayList<>();
        if (root == null)
            return verticalOrderTraversal;

        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        Queue<Node> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(new Node(root, 0, 0));

        while (!bfsQueue.isEmpty()){
            Node node = bfsQueue.remove();
            minHeap.add(node);

            if (node.treeNode.left != null)
                bfsQueue.add(new Node(node.treeNode.left, node.row + 1, node.column - 1));

            if (node.treeNode.right != null)
                bfsQueue.add(new Node(node.treeNode.right, node.row + 1, node.column + 1));
        }

        while (!minHeap.isEmpty()) {
            List<Integer> currVerticalColumn = new ArrayList<>();
            verticalOrderTraversal.add(currVerticalColumn);
            
            int leastColumn = minHeap.peek().column;
            
            while (!minHeap.isEmpty()  &&  minHeap.peek().column == leastColumn){
                Node node = minHeap.remove();
                currVerticalColumn.add(node.treeNode.val);
            }
        }
        return verticalOrderTraversal;
    }
    
    
    // Another class to hold the TreeNodes with their locations (with row & columns)
    private static class Node implements Comparable<Node>{
        TreeNode treeNode;
        int row, column;
        public Node(TreeNode treeNode, int row, int column) {
            this.treeNode = treeNode;
            this.row = row;
            this.column = column;
        }

        @Override
        public int compareTo(Node node){
            if (this.column != node.column)
                return this.column - node.column;
            else if (this.row != node.row)
                return this.row - node.row;
            else
                return this.treeNode.val - node.treeNode.val;
        }
    }
}