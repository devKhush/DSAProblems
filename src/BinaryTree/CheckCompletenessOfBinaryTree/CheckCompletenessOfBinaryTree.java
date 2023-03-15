package BinaryTree.CheckCompletenessOfBinaryTree;
import java.util.LinkedList;
import java.util.Queue;

// https://leetcode.com/problems/check-completeness-of-a-binary-tree/description/

public class CheckCompletenessOfBinaryTree {
    /***************************************** BFS Solution *****************************************
     * Time Complexity: O(n)
        * n -> number of nodes
     * Space Complexity: O(2 ^ log(n))
        * This is simply the number of nodes in last level
     */
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Tracks whether null node is found in level order traversal of tree or not.
        boolean nullFound = false;

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                TreeNode node = queue.remove();
                if (node == null)
                    nullFound = true;

                // If curr node is not null, and we found a null node before
                // It implies tree is not a complete binary tree
                if (node != null  && nullFound)
                    return false;

                if (node != null){
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
        }
        return true;
    }

    static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
