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
   public List<Integer> inorderTraversal(TreeNode root) {       
        ArrayList<Integer> inOrderTraversal = new ArrayList<>();
        // inOrder(root, inOrderTraversal);

        Stack<TreeNode> stack = new Stack<>();

        TreeNode node = root;
        while (node != null){
            stack.push(node);
            node = node.left;
        }

        while (!stack.isEmpty()){
            TreeNode treeNode = stack.pop();
            inOrderTraversal.add(treeNode.val);

            if (treeNode.right != null){
                treeNode = treeNode.right;

                while (treeNode != null){
                    stack.push(treeNode);
                    treeNode = treeNode.left;
                }
            }
        }
        return inOrderTraversal;
    }
    

    public void inOrder(TreeNode root, List<Integer> inOrderTraversal) {
        if (root!=null){
            inOrder(root.left, inOrderTraversal);
            inOrderTraversal.add(root.val);
            inOrder(root.right, inOrderTraversal);
        }    
    }
}