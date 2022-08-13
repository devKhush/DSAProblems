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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzagTraversal = new ArrayList<>();
        
        if (root == null)
            return zigzagTraversal;
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currLevel = new ArrayList<>();
            zigzagTraversal.add(currLevel);
            
            while (size-- > 0){
                TreeNode node = queue.remove();
                currLevel.add(node.val);
                
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        
        for (int i = 1; i < zigzagTraversal.size(); i += 2)
            reverse(zigzagTraversal.get(i));
        
        return zigzagTraversal;
    }
    
    private void reverse(List<Integer> arr){
        int n = arr.size();
        
        for (int i = 0; i < n/2; i++){
            int temp = arr.get(i);
            arr.set(i, arr.get(n - 1 - i));
            arr.set(n - 1 - i, temp);
        }
    }
}