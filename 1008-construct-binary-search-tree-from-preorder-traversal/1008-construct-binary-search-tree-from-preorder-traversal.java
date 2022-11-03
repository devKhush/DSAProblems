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
    
     public TreeNode bstFromPreorder(int[] preorder){
        int[] inorder = new int[preorder.length];
        System.arraycopy(preorder, 0, inorder, 0, preorder.length);
        Arrays.sort(inorder);

        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);

        return constructTree(preorder, 0, preorder.length-1,
                              inorder,  0,  inorder.length-1,  inorderMap);
    }

    private TreeNode constructTree(int[] preorder, int preStart, int preEnd,
                                   int[] inorder, int inStart, int inEnd,
                                   HashMap<Integer, Integer> inorderMap){
        if (preEnd < preStart)
            return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = inorderMap.get(preorder[preStart]);

        root.left = constructTree(preorder, preStart + 1, preStart + (rootIndex - inStart),
                                  inorder, inStart, rootIndex -1, inorderMap);
        root.right = constructTree(preorder, preStart + (rootIndex - inStart) + 1, preEnd,
                                   inorder, rootIndex + 1, inEnd, inorderMap);
        return root;
    }

    
    
    // Brute Force *********************************************************************
     private TreeNode insertBST(TreeNode root, int value){
        if (root == null)
            return new TreeNode(value);

        if (value < root.val)
            root.left = insertBST(root.left, value);
        if (root.val < value)
            root.right = insertBST(root.right, value);
        return root;
    }
    public TreeNode bstFromPreorder_Brute(int[] preorder) {
        TreeNode root = null;
        for (int node : preorder)
            root = insertBST(root, node);

        return root;   
    }
}