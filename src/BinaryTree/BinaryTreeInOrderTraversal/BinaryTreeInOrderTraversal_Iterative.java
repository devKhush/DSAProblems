package BinaryTree.BinaryTreeInOrderTraversal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// https://youtu.be/lxTGsVXjwvM
// https://takeuforward.org/data-structure/inorder-traversal-of-binary-tree/

public class BinaryTreeInOrderTraversal_Iterative {
    /*********************************** Iterative In-Order Traversal *********************************
     * Time Complexity: O(n)
        * n is number of nodes
     * Space Complexity: O(Height of the tree)
        * Max size of Stack will be of max. height of Tree, i.e, O(Tree's height)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // Output List for In order Traversal
        ArrayList<Integer> inOrderTraversal = new ArrayList<>();

        // Stack Used for Implementing a Recursive Solution
        Stack<TreeNode> stack = new Stack<>();

        // Insert all the Left child of the root node into the stack because Inorder Traversal
        // follows "Left Root Right". So, we first go to Left most nodes
        TreeNode node = root;
        while (node != null){
            stack.push(node);
            node = node.left;
        }

        while (!stack.isEmpty()){
            // One by one pop all nodes from the stack AND also add their values into the list
            node = stack.pop();
            inOrderTraversal.add(node.val);

            // If the right subtree of node is not null, then push all the left nodes of the Right
            // SubTree into the stack. We are following only "Left Root Right"
            if (node.right != null){
                TreeNode ptr = node.right;
                while (ptr != null){
                    stack.push(ptr);
                    ptr = ptr.left;
                }
            }
        }
        return inOrderTraversal;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
