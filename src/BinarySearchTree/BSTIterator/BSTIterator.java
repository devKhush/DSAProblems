package BinarySearchTree.BSTIterator;
import java.util.Stack;

// https://youtu.be/D2jMcmxU4bs

public class BSTIterator {
    /******************************* Efficient Solution ***********************************
     * Time Complexity: On average O(1) for each next() call & hasNext() call
     * Space Complexity: O(Tree's Height) ~ O(log(n))
        * Height of the Stack will be same as the Tree
     */
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        while (root != null){
            this.stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode node = this.stack.pop();

        TreeNode ptr = node.right;
        while (ptr != null){
            this.stack.push(ptr);
            ptr = ptr.left;
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }


    // Tree Node class
    static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
