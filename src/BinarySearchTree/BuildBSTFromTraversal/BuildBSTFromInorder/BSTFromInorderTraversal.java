package BinarySearchTree.BuildBSTFromTraversal.BuildBSTFromInorder;

public class BSTFromInorderTraversal {
    /******************************* Efficient Solution **********************************
     * Inorder Traversal -> Left Root Right
     * Intuition:
        * To construct, a height balanced tree take the middle node as root node.
        * Take the nodes from [low,mid) as left subtree node
        * Take the nodes from (mid,high] as right subtree node

     * Time complexity: O(3n)  ~  O(n)
        * Each node will be max visited 3 times.
     * Space complexity: O(n)
        * Recursion stack space in worst case.
     */
    public TreeNode sortedArrayToBST(int[] inorder) {
        return constructBST(inorder, 0, inorder.length - 1);
    }

    private TreeNode constructBST(int[] inorder, int low, int high){
        if (low > high)
            return null;

        int mid = low + (high - low)/2;
        TreeNode root = new TreeNode(inorder[mid]);

        root.left = constructBST(inorder, low, mid - 1);
        root.right = constructBST(inorder, mid + 1, high);
        return root;
    }


    private static class TreeNode{
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
