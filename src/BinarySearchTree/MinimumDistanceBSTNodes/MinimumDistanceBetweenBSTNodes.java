package BinarySearchTree.MinimumDistanceBSTNodes;
import java.util.ArrayList;

// https://leetcode.com/problems/minimum-distance-between-bst-nodes/

public class MinimumDistanceBetweenBSTNodes {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Approach 1 using IN-ORDER Traversal
    private ArrayList<Integer> inOrderTraversalValues = new ArrayList<>();
    public void inOrder(TreeNode root){
        if (root!=null){
            inOrder(root.left);
            inOrderTraversalValues.add(root.val);
            inOrder(root.right);
        }
    }
    public int minDiff(TreeNode root) {
        this.inOrder(root);
        int minDiff = Integer.MAX_VALUE;

        for (int i=0; i<inOrderTraversalValues.size(); i++){
            if (i != inOrderTraversalValues.size()-1){
                int currDiff = inOrderTraversalValues.get(i+1) - inOrderTraversalValues.get(i);
                minDiff = Math.min(minDiff, currDiff);
            }
        }
        return minDiff;
    }



    // Approach 2 faster
    private int minDistance = Integer.MAX_VALUE;
    private TreeNode previous = null;
    public void findMinDistance(TreeNode root){
        if (root!=null){
            findMinDistance(root.left);
            if (previous != null)
                minDistance = Math.min(minDistance, root.val - previous.val);
            previous = root;
            findMinDistance(root.right);
        }
    }

    public int minDiffInBST(TreeNode root) {
        findMinDistance(root);
        return minDistance;
    }
}
