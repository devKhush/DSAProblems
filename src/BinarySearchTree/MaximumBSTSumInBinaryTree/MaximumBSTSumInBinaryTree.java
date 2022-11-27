package BinarySearchTree.MaximumBSTSumInBinaryTree;

// PRE_REQUISITE: Largest BST in BT
// https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
// https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/discuss/2678525/JAVA-oror-TC-O(N)-oror-SC-O(1)-oror-Post-order-Traversal-oror-EASY-EXPLAINATION-oror
// https://youtu.be/X0oXMdtUDwo

public class MaximumBSTSumInBinaryTree {
    /********************************** Efficient Solution *********************************
     * Intuition:
        * To check BST:
            * Max node in left subtree should be smaller than root node
            * Min node in right subtree should be larger than root node
        * Check for the BSTs from the leaf, it will be easier to track all the BST
        * So, we use Postorder traversal
        * See video for in-depth explanation.

     * Time Complexity: O(n)
        * Single post order traversal
     * Space Complexity: O(Tree's Height)
        * Recursion stack space.
        * Space used by class objects are negligible
     */
    // Keep track of Maximum BST Sum, because the total combined BST sum might be lesser than previous
    // smaller BST due to negative sum in other BST
    int maxBSTSum = 0;

    public int maxSumBST(TreeNode root) {
        findMaximumSumBST(root);
        return maxBSTSum;
    }

    public NodeValue findMaximumSumBST(TreeNode root) {
        // An empty tree is a BST of sum 0
        if (root == null)
            return new NodeValue(0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        // Get values from left and right subtree of current tree.
        NodeValue left = findMaximumSumBST(root.left);
        NodeValue right = findMaximumSumBST(root.right);

        // Current node is greater than max in left AND smaller than min in right, it is a BST.
        if (left.maxNode < root.val && root.val < right.minNode){
            maxBSTSum = Math.max(maxBSTSum, left.sum + right.sum + root.val);
            return new NodeValue(left.sum + right.sum + root.val, Math.min(left.minNode, root.val), Math.max(right.maxNode, root.val));
        }

        // Otherwise, act smart and return [-inf, inf] so that parent can't be valid BST
        return new NodeValue(Math.max(left.sum, right.sum), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static class NodeValue{
        int sum;
        int maxNode, minNode;
        public NodeValue(int sum, int min, int max){
            this.sum = sum;
            this.minNode = min;
            this.maxNode = max;
        }
    }

    static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
