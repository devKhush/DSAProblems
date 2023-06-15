package BinarySearchTree.KLargestElementInBST;

public class KLargestElementInBST_MorrisInorder {
    /*********************************** Morris Solution *****************************************
     * Intuition:
     * Inorder Traversal "Left Root Right" of the BST is always in Sorted fashion.
     * So, we will do reverse Inorder traversal using Morris Inorder to traverse the
        BST in sorted fashion, till we haven't seen 'k' nodes in Inorder traversal.
     * We will use Reverse Morris Traversal

     * Time Complexity: O(k)
     * Space Complexity: O(1)
     */
    public int KthLargestNumber(TreeNode root, int k) {
        TreeNode node = root;

        while (node != null){
            if (node.right == null){
                k--;
                if (k == 0)
                    return node.val;
                node = node.left;
            }
            else{
                TreeNode ptr = node.right;
                while (ptr.left != null && ptr.left != node)
                    ptr = ptr.left;

                if (ptr.left == null){
                    ptr.left = node;
                    node = node.right;
                }
                else {
                    k--;
                    if (k == 0)
                        return node.val;
                    ptr.left = null;
                    node = node.left;
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
