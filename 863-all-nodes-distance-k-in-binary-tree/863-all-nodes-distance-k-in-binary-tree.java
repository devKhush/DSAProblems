/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    // DFS Solution *******************************************************************************88
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> distanceK = new ArrayList<>();
        if (root == null)
            return distanceK;

        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        markParentNodes_dfs(root, null, parents);

        HashSet<TreeNode> visited = new HashSet<>();

        findDistantKNodes_dfs(target, 0, k, visited, parents, distanceK);

        return distanceK;
    }

    private void findDistantKNodes_dfs(TreeNode node, int distance, int k, HashSet<TreeNode> visited,
                                       HashMap<TreeNode, TreeNode> parents, ArrayList<Integer> distanceK){
        if (node == null)
            return;

        if (distance == k){
            distanceK.add(node.val);
            return;
        }
        visited.add(node);

        if (node.left != null  &&  !visited.contains(node.left))
            findDistantKNodes_dfs(node.left, distance + 1, k, visited, parents, distanceK);

        if (node.right != null  &&  !visited.contains(node.right))
            findDistantKNodes_dfs(node.right, distance + 1, k, visited, parents, distanceK);

        if (parents.get(node) != null  &&  !visited.contains(parents.get(node)))
            findDistantKNodes_dfs(parents.get(node), distance + 1, k, visited, parents, distanceK);
    }

    private void markParentNodes_dfs(TreeNode node, TreeNode parent, HashMap<TreeNode, TreeNode> parents){
        if (node == null) return;

        parents.put(node, parent);

        markParentNodes_dfs(node.left, node, parents);
        markParentNodes_dfs(node.right, node, parents);
    }
    
    
    // BFS Solution *******************************************************************************88
    public List<Integer> distanceK_BFS(TreeNode root, TreeNode target, int k) {
        ArrayList<Integer> distanceK = new ArrayList<>();
        if (root == null)
            return distanceK;
        
        HashMap<TreeNode, TreeNode> parents = markParentNodes(root);
        
        HashSet<TreeNode> visited = new HashSet<>();
        int distance = 0;
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(target);
        visited.add(target);
        
        while (distance < k){
            int size = queue.size();
            
            while (size-- > 0){
                TreeNode node = queue.remove();
            
                if (node.left != null  &&  !visited.contains(node.left)){
                    queue.add(node.left);
                    visited.add(node.left);
                }
                if (node.right != null  &&  !visited.contains(node.right)){
                    queue.add(node.right);
                    visited.add(node.right);
                }
                if (parents.get(node) != null  &&  !visited.contains(parents.get(node))){
                    queue.add(parents.get(node));
                    visited.add(parents.get(node));
                }
            }
            distance++;
        }
        while (!queue.isEmpty()){
            TreeNode distanceKNode = queue.remove();
            distanceK.add(distanceKNode.val);
        }
        
        return distanceK;
    }
    
    private HashMap<TreeNode, TreeNode> markParentNodes(TreeNode root){
        HashMap<TreeNode, TreeNode> parents = new HashMap<>();
        parents.put(root, null);
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            
            if (node.left != null){
                parents.put(node.left, node);
                queue.add(node.left);
            }
            if (node.right != null){
                parents.put(node.right, node);
                queue.add(node.right);
            }
        }
        return parents;
    }
}