package BinaryTree.BinaryTreePostOrderTraversal;

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

public class BinaryTreePostOrderTraversal {
    public List<Integer> post = new ArrayList<Integer>();
    
    public void postOrder(TreeNode root){
        if (root!=null){
            postOrder(root.left);
            postOrder(root.right);
            post.add(root.val);
        }
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        postOrder(root);
        return post;
    }
}