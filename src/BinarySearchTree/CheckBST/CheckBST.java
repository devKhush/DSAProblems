package BinarySearchTree.CheckBST;

// https://youtu.be/f-sj7I5oXEI

public class CheckBST {
    /************************************ Recursive Inorder Solution *********************************
     * Intuition: Inorder is sorted for a BST. So, check is inorder is sorted or not.
     * Time Complexity: O(n)
     * Space Complexity: O(Tree's Height): Recursion Stack Space
     */
    public boolean inorder(TreeNode root, long[] value){
        if (root == null)
            return true;

        if (!inorder(root.left, value))
            return false;

        if (value[0] >= root.val)
            return false;
        value[0] = root.val;

        if (!inorder(root.right, value))
            return false;
        return true;
    }

    public boolean isValidBST_Recursive(TreeNode root) {
        return inorder(root, new long[]{Integer.MIN_VALUE - 1L});
    }


    /************************************ Morris Inorder Solution *********************************
     * Intuition: Inorder is sorted for a BST. So, check is inorder is sorted or not.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public boolean isValidBST_Morris(TreeNode root){
        long value = Long.MIN_VALUE;
        TreeNode node = root;

        while (node != null){
            if (node.left == null){
                if (node.val <= value)
                    return false;
                value = node.val;
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
                    ptr.right = null;
                    if (node.val <= value)
                        return false;
                    value = node.val;
                    node = node.right;
                }
            }
        }
        return true;
    }


    /************************************ Best Recursive Solution *********************************
     * Intuition:
        * Restrict a node's value in certain range. (low, high)
        * For left child --> (low, parent_value)
        * For right child --> (parent_value, high)
     * Time Complexity: O(n)
     * Space Complexity: O(Tree's Height): Recursion Stack Space
     */
    public boolean validateBST(TreeNode root, long low , long high){
        if (root == null)
            return true;

        // Check is value is in range
        if (low >= root.val  ||  root.val >= high)
            return false;

        // Validate Left & Right Tree
        return validateBST(root.left, low, root.val) && validateBST(root.right, root.val, high);
    }
    public boolean isValidBST(TreeNode root){
        return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static class  TreeNode{
        int val;
        TreeNode left, right;
    }
}
