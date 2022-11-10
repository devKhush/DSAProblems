package BinarySearchTree.BSTSuccessorAndPredecessor;

// Pre-requisite: Ceil in BST
// https://youtu.be/SXKAD2svfmI

public class BSTSuccessor {
    /********************************* Solution 1: Extreme Brute Force *******************************
     * Do an inorder traversal of BST (sorted traversal)
     * Search for the given node using binary search or linear search, and report its next node.
     * Time Complexity: O(n) + O(n)   OR   O(n) + O(log(n))
        * First one for linear search, & second one for binary search
     * Space Complexity: O(n)
        * For storing Inorder traversal
     */


    /************************************ Efficient Solution ******************************************
     * Same Logic as Ceil in BST
     * Time Complexity: O(Tree's height) ~ O(log(n))
     * Space Complexity: O(1)
     */
    public TreeNode bstSuccessor(TreeNode root, TreeNode node){
        TreeNode curr = root;
        TreeNode successor = null;

        while (curr != null){
            if (curr.val <= node.val)
                curr = curr.right;

            else if (curr.val > node.val){
                successor = curr;
                curr = curr.left;
            }
        }
        return successor;
    }

    private static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
