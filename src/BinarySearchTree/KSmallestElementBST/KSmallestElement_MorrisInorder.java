package BinarySearchTree.KSmallestElementBST;

// PRE_REQUISITE: "MORRIS INORDER TRAVERSAL"
// https://youtu.be/9TJYWh0adfk

public class KSmallestElement_MorrisInorder {
    /*********************************** Morris Solution *****************************************
     * Intuition:
        * Inorder Traversal "Left Root Right" of the BST is always in Sorted fashion.
        * So, we will do an Inorder traversal to traverse the BST in sorted fashion,
            till we haven't seen 'k' nodes in Inorder traversal.
        * We will use Morris Traversal

     * Time Complexity: O(k)
     * Space Complexity: O(1)
     */
    public int kthSmallest(TreeNode root, int k) {
        TreeNode node = root;
        while (node != null){
            if (node.left == null){
                k--;
                if (k == 0)
                    return node.val;
                node = node.right;
            }
            else{
                TreeNode ptr = node.left;
                while (ptr.right != null && ptr.right != node)
                    ptr = ptr.right;

                if (ptr.right == null){
                    ptr.right = node;
                    node = node.left;
                }
                else{
                    k--;
                    if (k == 0)
                        return node.val;
                    ptr.right = null;
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
