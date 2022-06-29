package BinaryTree.SumOfLeftLeaves;

// https://leetcode.com/problems/sum-of-left-leaves/
// https://www.geeksforgeeks.org/find-sum-left-leaves-given-binary-tree/

class SumOfLeftLeaves {
    /*
    ****************************** Efficient Solution *********************************
    * For every node in the tree, we need to keep track of whether the "Tree Node" is the left child
      or Right child of its "Parent Tree Node"
    * So, for each node we can add another parameter, whether it is the left child or right child of
      its parent. This can be done either by passing "Parent Node" itself OR a Boolean variable.
    * Time Complexity:  O(n)
      We will travel every node in the Binary Tree to reach the leaf node.
    * Space Complexity:  O(log(n))  =  O(Height of the Tree)
      At most Recursion Stack space will be height of the tree

      where 'n' is the no. of nodes in the Tree
     */

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;

        // return getLeftLeafNodeSum(root.left, root) + getLeftLeafNodeSum(root.right, root);
        return getLeftLeafNodeSum_(root.left, true) + getLeftLeafNodeSum_(root.right, false);
    }
    
    private int getLeftLeafNodeSum_(TreeNode root, boolean isLeftChild){
        if (root == null)
            return 0;
        
        if (root.left == null  && root.right == null){
            if (isLeftChild)
                return root.val;
            else
                return 0;
        }
        return getLeftLeafNodeSum_(root.left, true) + getLeftLeafNodeSum_(root.right, false);
    }
    
    
    private int getLeftLeafNodeSum(TreeNode root, TreeNode parent){
        if (root == null)
            return 0;
        
        if (root.left == null  && root.right == null){
            if (root == parent.left)
                return root.val;
            else
                return 0;
        }
        return getLeftLeafNodeSum(root.left, root) + getLeftLeafNodeSum(root.right, root);
    }

    public static class TreeNode {
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
}