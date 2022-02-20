package BinarySearchTree.MinimumDistanceBSTNodes;
import java.util.ArrayList;

// https://leetcode.com/problems/minimum-distance-between-bst-nodes/

class TreeNode {
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

public class MinimumDistanceBetweenBSTNodes {

    // Approach 1 using PRE-ORDER Traversal
    private ArrayList<Integer> allValues = new ArrayList<>();
    public void preOrder(TreeNode root){
        if (root!=null){
            preOrder(root.left);
            allValues.add(root.val);
            preOrder(root.right);
        }
    }
    public int minDiff(TreeNode root) {
        preOrder(root);
        int minDistance = Integer.MAX_VALUE;

        for (int i=0; i<allValues.size()-1; i++){
            int next = allValues.get(i+1);
            int curr = allValues.get(i);
            if (next - curr < minDistance)
                minDistance = next - curr;
        }
        return minDistance;
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
