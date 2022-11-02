package BinarySearchTree.LowestCommonAncestorInBST;

// https://youtu.be/cX_kPV_foZc

public class LowestCommonAncestorInBST {
    /********************************* Standard Method ******************************************
     * Check: LCA in Binary Tree
     * Time Complexity: O(n)
     * Space Complexity: O(Tree's Height)
     */
    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q){
        if (root == null)
            return null;
        if (root == p || root == q)
            return root;

        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);

        if (left == null)
            return right;
        if (right == null)
            return left;
        return root;
    }


    /********************************* Standard Method ******************************************
     * Intuition:
        * There can be 3 cases:
            1) Both the nodes lie on the Left
            2) Both the nodes lie on the Right
            3) One node lie on left & another lie on right subtree
     * Time Complexity: O(Tree's Height)
     * Space Complexity: O(Tree's Height)
     */
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);

        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);

        return root;
    }

    private TreeNode lowestCommonAncestor_iterative(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;

        while (node != null){
            if (node.val > p.val && node.val > q.val)
                node = node.left;
            else if (node.val < p.val && node.val < q.val)
                node = node.right;
            else
                return node;
        }
        return null;
    }


    static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
