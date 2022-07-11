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
    private ArrayList<Integer> traversal = new ArrayList<>();
    private int iterator;
    
    public void inOrder(TreeNode root){
        if (root!=null){
            inOrder(root.left);
            traversal.add(root.val);
            inOrder(root.right);
        }
    }

    public BSTIterator(TreeNode root) {
        inOrder(root);
        iterator = 0;
    }
    
    public int next() {
        return traversal.get(iterator++);
    }
    
    public boolean hasNext() {
        return iterator != traversal.size();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */