package BinaryTree.AllPossibleFullBinaryTrees;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/all-possible-full-binary-trees/
// https://leetcode.com/problems/all-possible-full-binary-trees/editorial/

public class AllPossibleFullBinaryTrees {
    /********************************** Recursion + Memoization ************************************
     * Intuition:
        * Recursion Thinking
     * Time Complexity: exponential
     * Space Complexity: O(n)
     */
    HashMap<Integer, List<TreeNode>> dp = new HashMap<>();
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> trees = new ArrayList<>();
        if (dp.containsKey(n))
            return dp.get(n);
        if (n == 1){
            trees.add(new TreeNode(0, null, null));
            dp.put(n, trees);
            return trees;
        }

        for (int i = 1; i < n; i += 2){
            List<TreeNode> leftTrees = allPossibleFBT(i);
            List<TreeNode> rightTrees = allPossibleFBT(n - i - 1);
            for (TreeNode left : leftTrees){
                for (TreeNode right : rightTrees){
                    TreeNode node = new TreeNode(0, left, right);
                    trees.add(node);
                }
            }
        }
        dp.put(n, trees);
        return trees;
    }



    static class TreeNode{
        int val;
        TreeNode left, right;
        public TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
