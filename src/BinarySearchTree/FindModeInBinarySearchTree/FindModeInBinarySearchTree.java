package BinarySearchTree.FindModeInBinarySearchTree;
import java.util.ArrayList;

// https://leetcode.com/problems/find-mode-in-binary-search-tree/description/

public class FindModeInBinarySearchTree {
    /************************************** Optimal Solution *************************************
     * Intuition:
        * To find mode, we need sorted sequence. So, do the inorder traversal of BST
        * Use simple concept of count, maxCount and prev to find modes
     * Time Complexity: O(n)
     * Space Complexity: O(1)
        * Ignoring dfs stack space
     */
    ArrayList<Integer> modes;
    int maxCount, count, prev;
    public ArrayList<Integer> findMode(TreeNode root) {
        maxCount = count = 0;
        prev = Integer.MIN_VALUE;
        modes = new ArrayList<>();

        dfs(root);

        return modes;
    }

    private void dfs(TreeNode root){
        if (root == null)
            return;

        dfs(root.left);

        if (prev == root.val){
            count++;
        }
        else{
            prev = root.val;
            count = 1;
        }
        if (count > maxCount){
            maxCount = count;
            modes.clear();
            modes.add(root.val);
        }
        else if (count == maxCount){
            modes.add(root.val);
        }
        dfs(root.right);
    }



    static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
