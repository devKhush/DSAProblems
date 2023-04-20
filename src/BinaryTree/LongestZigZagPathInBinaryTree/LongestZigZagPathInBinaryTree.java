package BinaryTree.LongestZigZagPathInBinaryTree;

// https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/description/
// https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/solutions/3433060/image-explanation-easy-complete-intuition-c-java-python/

public class LongestZigZagPathInBinaryTree {
    /********************************** DFS Solution ****************************************
     * Intuition: We follow only the DFS in ZIG-ZAG fashion

     * TC -> O(2*n)  ~  O(n)
        * Only two traversal of whole tree will be done
     * SC -> O(Tree's Height)  ~  O(n)
     */
    int maxZigZagLength = 0;
    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        dfs(root, 1, 0);
        return maxZigZagLength;
    }

    // direction 'dir' = 0 ==> represents left
    // direction 'dir' = 1 ==> represents right
    private void dfs(TreeNode node, int dir, int currZigZag){
        if (node == null)
            return;

        if (dir == 0){
            dfs(node.right, 1, currZigZag + 1);
            dfs(node.left, 0, 1);
        }
        else if (dir == 1){
            dfs(node.right, 1, 1);
            dfs(node.left, 0, currZigZag + 1);
        }
        maxZigZagLength = Math.max(maxZigZagLength, currZigZag);
    }


    static class TreeNode{
        int val;
        TreeNode left, right;
    }
}
