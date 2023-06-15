package BinarySearchTree.LargestBSTInBinaryTree;

// https://youtu.be/X0oXMdtUDwo
// https://www.codingninjas.com/codestudio/problems/893103?topList=striver-sde-sheet-problems

public class LargestBSTInBinaryTree {
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
    public int largestBST(TreeNode<Integer> root) {
        return findLargestBST(root).size;
    }

    private NodeValue findLargestBST(TreeNode<Integer> root){
        // An empty tree is a BST of size 0.
        if (root == null)
            return new NodeValue(0, Integer.MAX_VALUE, Integer.MIN_VALUE);

        // Get values from left and right subtree of current tree.
        NodeValue left = findLargestBST(root.left);
        NodeValue right = findLargestBST(root.right);

        // Current node is greater than max in left AND smaller than min in right, it is a BST.
        if (left.maxNode < root.data  &&  root.data < right.minNode){
            return new NodeValue(left.size + right.size + 1, Math.min(left.minNode, root.data),
                                        Math.max(right.maxNode, root.data));
        }

        // Otherwise, act smart and return [-inf, inf] so that parent can't be valid BST
        return new NodeValue(Math.max(left.size, right.size), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static class TreeNode<T>{
        T data;
        TreeNode<T> left, right;
    }

    private static class NodeValue{
        int size, minNode, maxNode;
        public NodeValue(int size, int minNode, int maxNode){
            this.size = size;
            this.minNode = minNode;
            this.maxNode = maxNode;
        }
    }
}
