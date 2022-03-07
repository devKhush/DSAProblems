package BinaryTree.DiameterOfBinaryTree;

// https://leetcode.com/problems/diameter-of-binary-tree/
// https://www.youtube.com/watch?v=Toe0UQMWhjM

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

public class DiameterOfBinaryTree {
    int diameter = 0;

    public int height(TreeNode root){
        if (root==null)
            return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        diameter = Integer.max(diameter, rightHeight+leftHeight);

        return 1+ Integer.max(leftHeight, rightHeight);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int treeHeight = height(root);
        return diameter;
    }
}
