/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean findTarget(TreeNode root, int k) {
         if (root == null)
            return false;

        BSTIterator forward = new BSTIterator(root, false);
        BSTIterator backward = new BSTIterator(root, true);

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


class BSTIterator {
    private final Stack<TreeNode> stack;
    private final boolean reverse;

    public BSTIterator(TreeNode root, boolean reverse) {
        this.stack = new Stack<>();
        this.reverse = reverse;
        pushAll(root);
    }

    public int next() {
        TreeNode node = this.stack.pop();
        
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
        while (node != null){
            this.stack.push(node);

            if (!this.reverse)
                node = node.left;
            else
                node = node.right;
        }
    }
}
