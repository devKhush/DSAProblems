package BinaryTree.FlattenBinaryTreeIntoLinkedList;

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


public class FlattenBinaryTreeIntoLinkedList {
    
    private ArrayList<TreeNode> preOrderTraversal = new ArrayList<>();
    
    public void preOrder(TreeNode root){
        if (root!=null){
            preOrderTraversal.add(root);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    
    public void flatten(TreeNode root) {
        preOrder(root);
        
        TreeNode head = new TreeNode(-1), ptr = head;
        
        for (int i = 0; i < preOrderTraversal.size(); i++){
            TreeNode temp = preOrderTraversal.get(i);
            temp.left = null;
            temp.right = null;
            ptr.right = temp;
            ptr = ptr.right;
        }
    }

    public static void main(String[] args) {

    }
}