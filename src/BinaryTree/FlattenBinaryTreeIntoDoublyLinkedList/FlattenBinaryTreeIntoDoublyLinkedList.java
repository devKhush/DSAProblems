package BinaryTree.FlattenBinaryTreeIntoDoublyLinkedList;
import java.util.Stack;

// Prerequisite: "Flatten Binary Tree Into Linked-List"
// https://www.codingninjas.com/codestudio/problems/893106

public class FlattenBinaryTreeIntoDoublyLinkedList {
    /************************************** Recursive Solution *******************************************
     * Intuition: Similar to the recursive solution of Flatten Binary Tree Into Singly Linked-List
     * Time Complexity: O(n)
     * Space Complexity: O(n)
        * Tree's height in worst case
     */
    public TreeNode head;
    public TreeNode BTtoDLL(TreeNode root) {
        head = null;
        dfs(root);
        return head;
    }
    public void dfs(TreeNode root){
        if (root == null)
            return;

        // Flatten right tree first
        dfs(root.right);

        if (head != null)
            head.left = root;
        root.right = head;
        head = root;

        // Flatten left tree later
        dfs(root.left);
    }


    /******************************************* Iterative Stack Solution ***************************
     * Intuition:
        * Iterative Stack solution is modified as in "Flatten Binary Tree Into Linked-List"
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static TreeNode BTtoDLL_stack(TreeNode root) {
        if (root == null)
            return null;

        // Head node of Doubly Linked-List
        TreeNode head = null;
        TreeNode prev = null;   // assign previous node using this pointer

        // Same In-order stack solution
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null){
            stack.push(node);
            node = node.left;
        }
        while (!stack.isEmpty()){
            node = stack.pop();
            if (node.right != null){
                TreeNode ptr = node.right;
                while (ptr != null){
                    stack.push(ptr);
                    ptr = ptr.left;
                }
            }

            if (head == null)
                head = node;
            node.right = !stack.isEmpty() ? stack.peek() : null;
            node.left = prev;
            prev = node;
        }
        return head;
    }

    /************************************* Morris Solution ***********************************
     * Intuition:
        * Morris Inorder is modified
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public static TreeNode BTtoDLL_morris(TreeNode root) {
        // Head node of Doubly Linked-List
        TreeNode head = null;
        TreeNode prev = null;       // assign previous node using this pointer

        // Modified Morris Inorder solution
        TreeNode node = root;
        while (node != null){
            if (node.left == null){
                if (head == null)
                    head = node;
                if (prev != null)
                    prev.right = node;
                node.left = prev;
                prev = node;
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
                    prev.right = node;
                    node.left = prev;
                    prev = node;
                    node = node.right;
                }
            }
        }
        return head;
    }



    static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
