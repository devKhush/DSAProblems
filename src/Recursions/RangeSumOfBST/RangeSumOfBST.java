package Recursions.RangeSumOfBST;

// https://leetcode.com/problems/range-sum-of-bst/submissions/

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

public class RangeSumOfBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root==null)
            return 0;
        if (root.val<=high && root.val>=low){
            int sum = root.val;
            int left = rangeSumBST(root.left, low, high);
            int right = rangeSumBST(root.right, low, high);
            return sum+left+right;
        }
        else if (root.val < low)
            return rangeSumBST(root.right, low, high);
        else
            return rangeSumBST(root.left, low, high);

    }
}
