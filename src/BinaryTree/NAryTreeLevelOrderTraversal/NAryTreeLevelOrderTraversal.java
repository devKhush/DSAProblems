package BinaryTree.NAryTreeLevelOrderTraversal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// PRE-REQUISITE: "BFS/LEVEL-ORDER-TRAVERSAL in Binary TREE"

class NAryTreeLevelOrderTraversal {
    /************************************* BFS *************************************************
     * Intuition:
        * Simple Generalization of BFS in Binary Tree
        * Model as "Real Directed Graph" with 'Source' as 'Root node'

     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null)
            return levels;

        // BFS Queue
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);


        while (!queue.isEmpty()){
            // Number of current in current level
            int size = queue.size();

            // Store the current level
            ArrayList<Integer> level = new ArrayList<>();
            levels.add(level);

            // Traverse the current level
            for (int i = 0; i < size; i++){
                Node node = queue.remove();
                level.add(node.val);
                
                for (Node child : node.children) {
                    queue.add(child);
                }
            }
        }
        return levels;
    }


    // N-Ary Tree Node
    private static class Node {
        public int val;
        public List<Node> children;
        public Node(int _val) {
            val = _val;
        }
    }
}