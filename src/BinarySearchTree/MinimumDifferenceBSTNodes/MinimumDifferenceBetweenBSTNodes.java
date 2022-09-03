package BinarySearchTree.MinimumDifferenceBSTNodes;
import java.util.ArrayList;

// https://leetcode.com/problems/minimum-distance-between-bst-nodes/

public class MinimumDifferenceBetweenBSTNodes {
    /********************************* Brute Force Approach ***************************************
     * Intuition:
        * We need to find the minimum difference between BST Nodes. General, Idea is to store all
            nodes in a list, and sort it (based on values).
        * After this, take the minimum difference b/w adjacent values, and return this minimum
            difference as the answer.
        * But the Tree is given is BST. Inorder of BST is always sorted.
        * So, no need of sorting nodes individually, In-order traversal can be used.

     * Time Complexity: O(n) + O(n)
        * O(n) time to calculate InOrder traversal of Tree
        * Another O(n) to compute the minimum diff. b/w nodes in Inorder traversal list of BST.
     * Space Complexity: O(n) + O(Tree's Height)
        * O(n) for maintaining list to store the nodes values in Inorder fashion.
        * O(Tree's Height) for DFS recursion stack space
     */
    public void inOrderTraversal(TreeNode root, ArrayList<Integer> inOrder){
        if (root == null)
            return;
        inOrderTraversal(root.left, inOrder);
        inOrder.add(root.val);
        inOrderTraversal(root.right, inOrder);
    }

    public int minDiff(TreeNode root) {
        ArrayList<Integer> inOrder = new ArrayList<>();

        // Find tInorder traversal (Already sorted traversal for BST)
        inOrderTraversal(root, inOrder);

        // Take the minimum difference b/w adjacent nodes values in Sorted Inorder traversal, and
        // return this minimum difference as the answer
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < inOrder.size() - 1; i++){
            int currDiff = inOrder.get(i + 1) - inOrder.get(i);
            minDiff = Math.min(minDiff, currDiff);
        }
        return minDiff;
    }


    /********************************* Efficient Approach 1 ***************************************
     * Intuition:
        * Read the above Brute Force Intuition First. This is Improvement of Brute Force Solution.
        * Instead of storing the BST nodes in a separate list to maintain Inorder traversal.
        * We can traverse the BST in Inorder traversal fashion, and compute the minimum difference
            b/w BST nodes while traversing the BST.
        * We can maintain the value/reference of "Previous node seen in Inorder traversal", and use
            it compute the required minimum difference.

     * Time Complexity: O(n)
        * O(n) time for InOrder traversal of Tree
     * Space Complexity: O(Tree's Height)
        * O(Tree's Height) for DFS recursion stack space
     */
    // Stores the minimum difference b/w BST nodes
    private int minDifference = Integer.MAX_VALUE;

    // Store the previous node in Inorder traversal w.r.t to current node
    private TreeNode previous = null;

    // Find the minimum difference b/w BST nodes
    public void findMinDifference(TreeNode root){
        if (root == null)
            return;

        // Traverse Left Subtree
        findMinDifference(root.left);

        // Update the "min. Difference" to the difference of values in "current node" and "previous node"
        // in Inorder traversal
        if (previous != null)
            minDifference = Math.min(minDifference, root.val - previous.val);

        // Mark the "Current node" as "Previous node" in inorder traversal, as 'current node' will
        // become the 'previous node' for next node in Inorder traversal.
        previous = root;

        // Traverse Right Subtree
        findMinDifference(root.right);
    }

    public int minDiffInBST(TreeNode root) {
        // Find the min. difference
        findMinDifference(root);
        return minDifference;
    }


    /********************************* Efficient Approach 2 ***************************************
     * Intuition:
        * Solution is same as previous Efficient Solution
        * Just the difference is that we will not use Global variables for this purpose.
        * We can just pass the "Minimum Difference" and "Previous node in Inorder traversal"
            by reference, using array.

     * Time Complexity: O(n)
        * O(n) time for InOrder traversal of Tree
     * Space Complexity: O(Tree's Height)
        * O(Tree's Height) for DFS recursion stack space
     */
    public int getMinimumDifference(TreeNode root) {
        // Pass the "Minimum Difference" and "Previous node in Inorder traversal"
        // by reference, using array.
        int[] minDiff = {Integer.MAX_VALUE};
        TreeNode[] prev = {null};

        // Find the min. difference
        minimumDifference(root, minDiff, prev);

        return minDiff[0];      // Return the min. difference
    }

    public void minimumDifference(TreeNode node, int[] minDiff, TreeNode[] prev){
        if (node == null)
            return;

        // Traverse Left Subtree
        minimumDifference(node.left, minDiff, prev);

        // Update the "min. Difference" to the difference of values in "current node" and "previous node"
        // in Inorder traversal
        if (prev[0] != null)
            minDiff[0] = Math.min(minDiff[0], node.val - prev[0].val);

        // Mark the "Current node" as "Previous node" in inorder traversal, as 'current node' will
        // become the 'previous node' for next node in Inorder traversal.
        prev[0] = node;

        // Traverse Right Subtree
        minimumDifference(node.right, minDiff, prev);
    }


    // Tree Node
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
