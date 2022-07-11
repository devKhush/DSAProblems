package Stacks.BinarySearchTreeIterator.UsingStack;

import Stacks.BinarySearchTreeIterator.TreeNode;
import java.util.Stack;
// https://www.youtube.com/watch?v=D2jMcmxU4bs

// This Question is same as Iterative-Inorder traversal of BST using Stack
// We are actually maintaining In-order traversal here, but putting right ones later

public class BSTIterator {
    public Stack<TreeNode> inOrderIterator = new Stack<>();

    public void inOrderIterator(TreeNode root){
        while (root!=null){
            inOrderIterator.push(root);
            root = root.left;
        }
    }

    public BSTIterator(TreeNode root) {
        inOrderIterator(root);
    }

    public int next() {
        TreeNode value = inOrderIterator.pop();

        inOrderIterator(value.right);
        return value.val;
    }

    public boolean hasNext() {
        return !inOrderIterator.isEmpty();
    }
}
