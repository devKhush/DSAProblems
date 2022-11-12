package BinarySearchTree.TwoSumInBST;
import java.util.ArrayList;

// https://youtu.be/ssL3sHwPeb4

public class TwoSumInBST {
    /********************************** Brute Force ********************************************
     * Intuition:
        * Inorder Traversal is sorted traversal
        * Question reduces to "Two Sum in sorted array"

     * Time Complexity: O(n) + O(n)  =  O(n)
        * O(n) for inorder traversal
        * O(n) for Two sum check in sorted array
     * Space Complexity: O(n)
        * Array required for storing inorder traversal
     */
    public void inorderTraversal(TreeNode root, ArrayList<Integer> inorder){
        if (root == null) return;
        inorderTraversal(root.left, inorder);
        inorder.add(root.val);
        inorderTraversal(root.right, inorder);
    }

    public boolean twoSumBST_BruteForce(TreeNode root, int k) {
        ArrayList<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);

        // Two Sum in Sorted array
        int low = 0, high = inorder.size() - 1;
        while (low < high){
            if (inorder.get(low) + inorder.get(high) == k)
                return true;
            if (inorder.get(low) + inorder.get(high) > k)
                high--;
            else
                low++;
        }
        return false;
    }


    /***************************************** Efficient Solution **********************************
     * Intuition:
        * Idea is to use "Two Sum in sorted array", but without using an array.
        * We use BST iterators, avoid use of arrays (external space).
        * In above solution (brute force), both low & high together travels whole array once.
        * Same will be applicable for BST iterators too. Both the iterators together will traverse the
            whole BST (inorder traversal).

     * Time Complexity:  O(n)
        * In worst case, whole BST is traversed only once.
        * No need to compute inorder traversal, BST iterator does this.
     * Space Complexity: 2 * O(Tree's height)   ~  2 * O(log(h))
        * Two stacks in BST iterators, each of height O(log(h)).
     */
    public boolean twoSumBST(TreeNode root, int k) {
        if (root == null)
            return false;

        // Forward BST Iterator (gives next)
        BSTIterator forward = new BSTIterator(root, false);

        // Backward BST Iterator (gives before)
        BSTIterator backward = new BSTIterator(root, true);

        // Same logic as "Two Sum in sorted array"
        int left = forward.next();
        int right = backward.next();

        while (left < right){
            if (left + right == k)
                return true;

            if (left + right > k)
                right = backward.next();
            else
                left = forward.next();
        }
        return false;
    }
}

