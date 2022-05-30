package BinaryTree.PathSum;

class PathSum {

    // Simple logic as in DP

    private boolean pathSum(TreeNode root, int targetSum, int sum){
        if (root == null)
            return false;
        
        if (root.left == null && root.right == null)
            return root.val + sum == targetSum ? true : false;

        boolean foundOnLeftTree = pathSum(root.left, targetSum, sum + root.val);
        if (foundOnLeftTree)
            return true;

        boolean foundOnRightTree = pathSum(root.right, targetSum, sum + root.val);
        return foundOnRightTree;
    }
    
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return this.pathSum(root, targetSum, 0);
    }


    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}