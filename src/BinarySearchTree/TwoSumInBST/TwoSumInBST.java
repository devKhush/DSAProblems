package BinarySearchTree.TwoSumInBST;
import java.util.ArrayList;
import java.util.HashSet;

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

    public boolean twoSumBST_BruteForce1(TreeNode root, int k) {
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


    /********************************** DFS Solution ********************************************
     * Intuition:
        * Same logic as two sum in an array (Two Sum - I)
        * Carry a set

     * Time Complexity: O(n)
        * O(n) for inorder traversal
     * Space Complexity: O(n)
        * Array required for storing inorder traversal
     */
    public boolean twoSumBST(TreeNode root, int k, HashSet<Integer> set) {
        if (root == null)
            return false;

        // Inorder for left
        if (twoSumBST(root.left, k, set))
            return true;

        // Check for current node
        if (set.contains(k - root.val))
            return true;
        set.add(root.val);

        // Inorder for right
        if (twoSumBST(root.right, k, set))
            return true;
        return false;
    }

    /********************************** Morris Traversal Solution **************************************
     * Intuition:
        * Same logic as two sum in an array (Two Sum - I)
        * Carry a set

     * Time Complexity: O(n)
        * O(n) for Morris inorder traversal
     * Space Complexity: O(n)
        * Set required for storing hashed values
     */
    public boolean findTarget_morris(TreeNode root, int k) {
        TreeNode node = root;
        HashSet<Integer> set = new HashSet<>();
        while (node != null){
            if (node.left == null){
                if (set.contains(k - node.val))
                    return true;
                set.add(node.val);
                node = node.right;
            }
            else{
                TreeNode ptr = node.left;
                while (ptr.right != null && ptr.right != node)
                    ptr = ptr.right;

                if (ptr.right == null){
                    ptr.right = node;
                    node = node.left;
                }
                else{
                    if (set.contains(k - node.val))
                        return true;
                    set.add(node.val);
                    ptr.right = null;
                    node = node.right;
                }
            }
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
    public boolean twoSumInBST(TreeNode root, int k) {
        BSTIterator leftIterator = new BSTIterator(root, false);
        BSTIterator rightIterator = new BSTIterator(root, true);

        while (leftIterator.peek() < rightIterator.peek()){
            if (leftIterator.peek() + rightIterator.peek() == k)
                return true;
            else if (leftIterator.peek() + rightIterator.peek() > k)
                rightIterator.pop();
            else
                leftIterator.pop();
        }
        return false;
    }
}

