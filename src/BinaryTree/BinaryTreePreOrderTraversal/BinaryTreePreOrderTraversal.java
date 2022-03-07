package BinaryTree.BinaryTreePreOrderTraversal;
import java.util.ArrayList;
import java.util.List;

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

public class BinaryTreePreOrderTraversal {
    List<Integer> preOrderTraversal = new ArrayList<Integer>();

    
    public void preOrder(TreeNode root) {
        if (root!=null){
            preOrderTraversal.add(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }
    

    public List<Integer> preorderTraversal(TreeNode root) {
        preOrder(root);
        return preOrderTraversal;
    }
}