package BinaryTree.SumRootToLeafNumbers;

// https://leetcode.com/problems/sum-root-to-leaf-numbers/description/

public class SumRootToLeafNumbers {
    /*************************************** DFS Solution ******************************************
     * Time Complexity: O(n)
        * DFS of Tree
     * Space Complexity: O(Tree's Height)
        * Recursion_stack_space
     */
    public int sumNumbers(TreeNode root) {
        return treeSum(root, 0);
    }

    private int treeSum(TreeNode root, int sumPrev){
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return sumPrev * 10 + root.val;

        int sumCurr = sumPrev * 10 + root.val;
        int left = treeSum(root.left, sumCurr);
        int right = treeSum(root.right, sumCurr);
        return left + right;
    }


    // TreeNode class Structure
    static public class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
