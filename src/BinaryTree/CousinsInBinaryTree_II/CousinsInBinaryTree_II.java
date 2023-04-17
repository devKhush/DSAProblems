package BinaryTree.CousinsInBinaryTree_II;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

// https://leetcode.com/problems/cousins-in-binary-tree-ii/description/

public class CousinsInBinaryTree_II {
    /**************************************** Efficient BFS Solution ************************************
     * Intuition:
        * For the current level, find the sum of next level nodes. (child nodes)
        * Then at child nodes, subtract the sum of all nodes at that level by the sum of non sibling nodes
        * Sum of non-sibling nodes (i.e, nodes from same parents can be stored in the HashMap)

     * TC -> O(n)
     * SC -> O(n)
     */
    public TreeNode replaceValueInTree(TreeNode root) {
        if (root == null)
            return null;

        Queue<TreeNode[]> queue = new ArrayDeque<>();
        queue.add(new TreeNode[] {root, root});

        int currLevelChildSum = root.val;
        HashMap<TreeNode, Integer> childSumMap = new HashMap<>();
        childSumMap.put(root, root.val);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int nextLevelChildSum = 0;

            for (int i = 0; i < size; i++) {
                int nodeSum = 0;
                TreeNode node = queue.peek()[0];
                TreeNode parent = queue.remove()[1];

                if (node.left != null) {
                    queue.add(new TreeNode[] {node.left, node});
                    nodeSum += node.left.val;
                }
                if (node.right != null) {
                    queue.add(new TreeNode[] {node.right, node});
                    nodeSum += node.right.val;
                }
                node.val = currLevelChildSum - childSumMap.get(parent);
                childSumMap.put(node, nodeSum);
                nextLevelChildSum += nodeSum;
            }
            currLevelChildSum = nextLevelChildSum;
        }
        return root;
    }


    static class TreeNode{
        TreeNode left, right;
        int val;
    }
}
