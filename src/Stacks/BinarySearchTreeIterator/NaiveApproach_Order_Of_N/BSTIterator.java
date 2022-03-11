package Stacks.BinarySearchTreeIterator.NaiveApproach_Order_Of_N;

import java.util.ArrayList;

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

public class BSTIterator {
    
    private ArrayList<Integer> traversal = new ArrayList<>();
    private int iterator;
    
    public void inOrder(TreeNode root){
        if (root!=null){
            inOrder(root.left);
            traversal.add(root.val);
            inOrder(root.right);
        }
    }

    public BSTIterator(TreeNode root) {
        inOrder(root);
        iterator = -1;
    }
    
    public int next() {
        iterator++;
        return traversal.get(iterator);
    }
    
    public boolean hasNext() {
        return iterator+1 < traversal.size();
    }
}
