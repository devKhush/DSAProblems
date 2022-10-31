package BinarySearchTree.KLargestElementInBST;
import java.util.Stack;

public class KLargestElementInBST {
    /*********************************** Recursive Solution *****************************************
     * Intuition:
     * Reverse Inorder Traversal "Right Root Left" of the BST is always in Sorted fashion.
     * So, we will do reverse Inorder traversal to traverse the BST in sorted fashion,
        till we haven't seen 'k' nodes in Inorder traversal.
     * Time Complexity: O(k)
     * Space Complexity: O(Tree's Height)
     */
    public TreeNode reverseInorder(TreeNode root, int[] count){
        if (root == null) return null;

        TreeNode right = reverseInorder(root.right, count);
        if (right != null)
            return right;

        count[0]--;
        if (count[0] == 0) return root;

        TreeNode left = reverseInorder(root.left, count);
        return left;
    }

    public int KthLargestNumber(TreeNode root, int k) {
        return reverseInorder(root, new int[]{k}).val;
    }



    /****************************************** Iteraive Solution ****************************
     * Intuition:
        Same as Recursive Solution
     * Time Complexity: O(k)
     * Space Complexity: O(Tree's Height)
     */
    public int kthSmallest_BST(TreeNode root, int K) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;

        while (node != null){
            stack.push(node);
            node = node.right;
        }

        while (!stack.isEmpty()){
            node = stack.pop();
            K--;
            if (K == 0)
                return node.val;

            if (node.left != null){
                node = node.left;
                while (node != null){
                    stack.push(node);
                    node = node.right;
                }
            }
        }
        return -1;
    }


    // Tree Node
    private static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}
