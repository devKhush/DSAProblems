package BinaryTree.PopulateNextRightPointersOfTree;
import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

public class PopulateNextRightPointersOfTree {
    /*************************************** Simple BFS ******************************************
     * TC -> O(n)
     * SC -> O(n)
     */
    public Node connect(Node root) {
        if (root == null)
            return null;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                Node node = queue.remove();
                if (i != size - 1)
                    node.next = queue.peek();
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return root;
    }

    static class Node {
        public int val;
        public Node left, right, next;
    }
}
