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
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        HashMap<Integer, Integer> inOrderMap = new HashMap<>();
        
        for (int i = 0; i < inOrder.length; i++){
            inOrderMap.put(inOrder[i], i);
        }
        
        TreeNode root = buildTree(0, inOrder.length -1, inOrder, 0, postOrder.length -1, postOrder, inOrderMap);
        return root;
    }
    
    private TreeNode buildTree(int inStart, int inEnd, int[] inOrder, int postStart, int postEnd, int[] postOrder, HashMap<Integer, Integer> inOrderMap){
        if (inStart > inEnd)
            return null;
        
        TreeNode root = new TreeNode(postOrder[postEnd]);
        
        int rootIndex = inOrderMap.get(root.val);
        int rightNodesCnt = inEnd - rootIndex;
        
        root.left = buildTree(inStart, rootIndex - 1, inOrder, postStart, postEnd - 1 - rightNodesCnt, postOrder, inOrderMap);
        root.right = buildTree(rootIndex + 1, inEnd, inOrder, postEnd - rightNodesCnt, postEnd -1, postOrder, inOrderMap);
        return root;
    }
}
