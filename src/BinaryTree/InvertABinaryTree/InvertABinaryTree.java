package BinaryTree.InvertABinaryTree;

class InvertABinaryTree {
    // Simple Solution
    // TC -> O(n)   n -> no. of nodes int binary tree
    // SC -> o(n)
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }
    
    private void invert(TreeNode root){
        if (root == null)
            return;
        
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        
        invert(root.right);
        invert(root.left);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}