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
class BSTIterator {
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

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */