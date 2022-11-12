package BinarySearchTree.TwoSumInBST;
import java.util.Stack;

// Pre-Requisite: BST Iterator next version
// Instead of two separate classes for BST iterator, we can do it in single class too.

public class BSTIterator {
    private final Stack<TreeNode> stack;
    private final boolean reverse;

    public BSTIterator(TreeNode root, boolean reverse) {
        this.stack = new Stack<>();
        this.reverse = reverse;
        pushAll(root);
    }

    public int next() {
        TreeNode node = this.stack.pop();

        // Push the Left nodes of Right subtree if reverse is false
        // Else, push the Right nodes of Left subtree when reverse is true
        if (!this.reverse)
            pushAll(node.right);
        else
            pushAll(node.left);

        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushAll(TreeNode node){
        // Push the Left nodes of Right subtree if reverse is false
        // Else, push the Right nodes of Left subtree when reverse is true
        while (node != null){
            this.stack.push(node);

            if (!this.reverse)
                node = node.left;
            else
                node = node.right;
        }
    }
}
