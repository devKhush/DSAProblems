package BinaryTree.SumOfLeftLeaves;

// https://leetcode.com/problems/sum-of-left-leaves/
// https://www.geeksforgeeks.org/find-sum-left-leaves-given-binary-tree/

import java.util.ArrayDeque;
import java.util.Queue;

class SumOfLeftLeaves {
    /******************************* Efficient DFS Solution *********************************
    * For every node in the tree, we need to keep track of whether the "Tree Node" is the left child
      or Right child of its "Parent Tree Node"
    * So, for each node we can add another parameter, whether it is the left child or right child of
      its parent. This can be done either by passing "Parent Node" itself OR a Boolean variable.
    * Time Complexity:  O(n)
      We will travel every node in the Binary Tree to reach the leaf node.
    * Space Complexity:  O(log(n))  =  O(Height of the Tree)
      At most Recursion Stack space will be height of the tree

      where 'n' is the no. of nodes in the Tree
     */

    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    public int dfs(TreeNode root, boolean isLeftChild){
        if (root == null)
            return 0;

        // If thr node is Left Leaf node return its value
        if (root.left == null  &&  root.right == null  &&  isLeftChild)
            return root.val;

        return dfs(root.left, true) + dfs(root.right, false);
    }


    /******************************* Efficient BFS Solution *********************************
     * For every node in the tree, we need to keep track of whether the "Tree Node" is the left child
        or Right child of its "Parent Tree Node"
     * So, for each node we can add another property, whether it is the left child or right child of
        its parent. This can be done either by maintaining a boolean variable

     * Time Complexity:  O(n)
        * We will travel every node in the Binary Tree to reach the leaf node.
     * Space Complexity:  O(n/2)  =  O(n)
        * Size of BFS Queue
     */
    public int sumOfLeftLeaves_BFS(TreeNode root) {
        if (root == null)
            return 0;

        // Sum of Left leaf nodes
        int sum = 0;

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(root, false));

        while (!queue.isEmpty()){
            Pair pair = queue.remove();

            // Add left child
            if (pair.node.left != null)
                queue.add(new Pair(pair.node.left, true));

            // Add right child
            if (pair.node.right != null)
                queue.add(new Pair(pair.node.right, false));

            // If thr node is Left Leaf node, add its value
            if (pair.node.left == null  && pair.node.right == null  && pair.isLeftChild)
                sum += pair.node.val;
        }
        return sum;
    }


    // Pair class for Tree Node and Boolean for "is Left Child"
    static private class Pair{
        TreeNode node;
        boolean isLeftChild;
        public Pair(TreeNode node, boolean isLeftChild){
            this.node = node;
            this.isLeftChild = isLeftChild;
        }
    }

    // Tree Node
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }
}