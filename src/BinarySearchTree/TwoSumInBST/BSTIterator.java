package BinarySearchTree.TwoSumInBST;
import java.util.Stack;

// Pre-Requisite: BST Iterator next version
// Instead of two separate classes for BST iterator, we can do it in single class too.

public class BSTIterator{
    Stack<TreeNode> stack;
    boolean reverse;
    public BSTIterator(TreeNode root, boolean reverse) {
        this.stack = new Stack<>();
        this.reverse = reverse;
        pushAll(root);
    }

    public int peek(){
        return stack.peek().val;
    }

    public void pop(){
        TreeNode node = stack.pop();
        if (!reverse)
            pushAll(node.right);
        else
            pushAll(node.left);
    }

    // Push the Left nodes of Right subtree if reverse is false
    // Else, push the Right nodes of Left subtree when reverse is true
    public void pushAll(TreeNode node){
        while (node != null){
            stack.push(node);
            if (!reverse)
                node = node.left;
            else
                node = node.right;
        }
    }
}