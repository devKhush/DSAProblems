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
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> rootToLeafPaths = new ArrayList<>();
        
        dfs_PreOrder(root, rootToLeafPaths, new ArrayList<>());
        
        return rootToLeafPaths;
    }
    
    public void dfs_PreOrder(TreeNode node, ArrayList<String> rootToLeafPaths, ArrayList<Integer> path){
        if (node == null)
            return;
        
        path.add(node.val);
        
        if (node.left == null && node.right == null){
            StringBuilder sb = new StringBuilder();
            int size = path.size();
            
            for (int i = 0; i < size - 1; i++){
                sb.append(path.get(i));
                sb.append("->");
            }
            sb.append(path.get(size - 1));
            rootToLeafPaths.add(sb.toString());
        }
                      
        
        dfs_PreOrder(node.left, rootToLeafPaths, path);
        dfs_PreOrder(node.right, rootToLeafPaths, path);
        
        path.remove(path.size() - 1);
    }
}