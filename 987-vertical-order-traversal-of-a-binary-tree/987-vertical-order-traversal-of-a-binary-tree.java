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
        dfsPreOrder(root, 0, 0, minHeap);

        while (!minHeap.isEmpty()) {
            List<Integer> currVerticalColumn = new ArrayList<>();
            verticalOrderTraversal.add(currVerticalColumn);

            int nextLeastColumn = minHeap.peek().column;

            while (!minHeap.isEmpty()  &&  minHeap.peek().column == nextLeastColumn){
                Node node = minHeap.remove();
                currVerticalColumn.add(node.treeNode.val);
            }
        }
        return verticalOrderTraversal;
    }
    
    
     public void dfsPreOrder(TreeNode root, int row, int column, PriorityQueue<Node> minHeap){
        if (root == null)
            return;
        minHeap.add(new Node(root, row, column));
        dfsPreOrder(root.left, row + 1, column - 1, minHeap);
        dfsPreOrder(root.right, row + 1, column + 1, minHeap);
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