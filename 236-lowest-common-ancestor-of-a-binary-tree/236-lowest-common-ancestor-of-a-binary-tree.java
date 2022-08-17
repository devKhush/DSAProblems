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
    // Efficient Solution **********************************************************
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        
        if (root == p || root == q)
            return root;
        
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);
        
        if (leftResult == null)
            return rightResult;
        else if (rightResult == null)
            return leftResult;
        else
            return root;
    }
    
    
    // Brute Force Solution ****************************************************************
    public TreeNode lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> pathToNodeP = new ArrayList<>();
        ArrayList<TreeNode> pathToNodeQ = new ArrayList<>();

        findPathFromRootToNode(root, p, pathToNodeP);
        findPathFromRootToNode(root, q, pathToNodeQ);

        int minSize = Math.min(pathToNodeP.size(), pathToNodeQ.size());

        for (int i = 0; i <= minSize; i++){
            if (i == minSize)
                return pathToNodeP.get(minSize - 1);
            if (pathToNodeP.get(i) != pathToNodeQ.get(i))
                return pathToNodeP.get(i - 1);
        }
        return null;
    }

    private boolean findPathFromRootToNode(TreeNode node, TreeNode destination, ArrayList<TreeNode> path){
        if (node == null)
            return false;

        path.add(node);

        if (node == destination)
            return true;

        if (findPathFromRootToNode(node.left, destination, path))
            return true;
        if (findPathFromRootToNode(node.right, destination, path))
            return true;

        path.remove(path.size() - 1);
        return false;
    }
}