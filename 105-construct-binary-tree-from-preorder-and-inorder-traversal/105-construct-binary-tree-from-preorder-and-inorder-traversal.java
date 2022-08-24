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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++){
            inOrderMap.put(inorder[i], i);
        }
        
        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inOrderMap);
        return root;
    }
    
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> inOrderMap){
        if (inStart > inEnd)
            return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        
        int rootIndex = inOrderMap.get(root.val);
        int leftNodes = rootIndex - inStart;
        
        root.left = buildTree(preorder, preStart + 1, preStart + leftNodes, inorder, inStart, rootIndex - 1, inOrderMap);
        root.right = buildTree(preorder, preStart + 1 + leftNodes, preEnd, inorder, rootIndex + 1, inEnd, inOrderMap);
        
        return root;
    }
}