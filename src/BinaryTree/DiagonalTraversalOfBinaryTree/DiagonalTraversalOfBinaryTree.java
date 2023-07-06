package BinaryTree.DiagonalTraversalOfBinaryTree;
import java.util.*;
import javafx.util.Pair;

// https://www.codingninjas.com/studio/problems/diagonal-traversal-of-a-binary-tree_920477
// https://www.geeksforgeeks.org/diagonal-traversal-of-binary-tree/

public class DiagonalTraversalOfBinaryTree {
    /************************************* DFS ****************************************
     * Intuition:
        * Simple Recursion thinking
        * Diagonal remains same on moving right on the same diagonal level.
        * So, one unique ID for each diagonal
     * Time Complexity: O(n)
        * DFS
     * Space Complexity: O(n)
        * hashMap to maintain diagonals traversal
     */
    public ArrayList<Integer> diagonalTraversal_dfs(TreeNode root) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        dfs(root, 0, map);

        ArrayList<Integer> diagonalTraversal = new ArrayList<>();
        int diagonal = 0;
        while (map.containsKey(diagonal)){
            diagonalTraversal.addAll(map.get(diagonal));
            diagonal++;
        }
        return diagonalTraversal;
    }

    private void dfs(TreeNode root, int diagonal, HashMap<Integer, ArrayList<Integer>> map){
        if (root == null)
            return;

        ArrayList<Integer> currDiagonal = map.getOrDefault(diagonal, new ArrayList<>());
        currDiagonal.add(root.data);
        map.put(diagonal, currDiagonal);

        dfs(root.left, diagonal + 1, map);
        dfs(root.right, diagonal, map);
    }

    /************************************* BFS *****************************************
     * This solution will work only if the order of nodes in same diagonal doesn't matter

     * Time Complexity: O(n)
        * BFS
     * Space Complexity: O(n)
        * Queue & HashMap to maintain diagonals traversal
     */
    public ArrayList<Integer> diagonalTraversal_bfs(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(root, 0));

        while (!queue.isEmpty()){
            Pair<TreeNode, Integer> pair = queue.remove();
            TreeNode node = pair.getKey();
            int diagonal = pair.getValue();

            ArrayList<Integer> currDiagonal = map.getOrDefault(diagonal, new ArrayList<>());
            currDiagonal.add(node.data);
            map.put(diagonal, currDiagonal);

            if (node.left != null)
                queue.add(new Pair<>(node.left, diagonal + 1));
            if (node.right != null)
                queue.add(new Pair<>(node.right, diagonal));
        }

        ArrayList<Integer> diagonalTraversal = new ArrayList<>();
        int diagonal = 0;
        while (map.containsKey(diagonal)){
            diagonalTraversal.addAll(map.get(diagonal));
            diagonal++;
        }
        return diagonalTraversal;
    }

    static public class TreeNode{
        int data;
        TreeNode left, right;
        public TreeNode(int data) {
            this.data = data;
        }
    }
}
