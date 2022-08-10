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
    public List<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> postOrderTraversal = new ArrayList<>();
        if (root == null)
            return postOrderTraversal;

        Stack<TreeNode> s1 = new Stack<>();
        s1.push(root);

        while (!s1.isEmpty()){
            TreeNode node = s1.pop();
            postOrderTraversal.add(0, node.val);
            
            if (node.left != null)
                s1.push(node.left);

            if (node.right != null)
                s1.push(node.right);
        }
        return postOrderTraversal;
    }
    

    public void postOrder(TreeNode root, List<Integer> postOrderTraversal){
        if (root!=null){
            postOrder(root.left, postOrderTraversal);
            postOrder(root.right, postOrderTraversal);
            postOrderTraversal.add(root.val);
        }
    }

}