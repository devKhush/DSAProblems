package BinaryTree.VerticalOrderTraversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VerticalOrderTraversal_Compact {
    /***************************************** DFS + Sorting Solution ***********************************
     * Time Complexity: O(n * log(n))
        * DFS Traversal for O(n) time
        * Sorting and adding nodes to the answer takes O(n*log(n)) time
     * Space Complexity: O(n)
        * DFS Recursion stack
        * HashMap to store all the verticals in the order of vertical
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // Map to store all the verticals
        HashMap<Integer, ArrayList<Pair>> verticalMap = new HashMap<>();
        int[] minVertical = {Integer.MAX_VALUE};

        // DFS Traversal
        dfs(0, 0, root, verticalMap, minVertical);

        // Add all the verticals in sorted fashion of rows and columns
        List<List<Integer>> verticalOrder = new ArrayList<>();
        int vertical = minVertical[0];
        while (verticalMap.containsKey(vertical)){
            // Sort all the nodes in a column based on row no. first and then node's value
            ArrayList<Pair> column = verticalMap.get(vertical);
            column.sort((a, b) -> a.row != b.row ? a.row - b.row : a.node.val - b.node.val);

            ArrayList<Integer> arr = new ArrayList<>();
            for (Pair pair : column){
                arr.add(pair.node.val);
            }
            verticalOrder.add(arr);
            vertical++;
        }
        return verticalOrder;
    }

    private void dfs(int row, int col, TreeNode node, HashMap<Integer, ArrayList<Pair>> map, int[] minVertical){
        if (node == null)
            return;
        ArrayList<Pair> list = map.getOrDefault(col, new ArrayList<>());
        list.add(new Pair(row, col, node));
        map.put(col, list);
        minVertical[0] = Math.min(minVertical[0], col);

        dfs(row + 1, col - 1, node.left, map, minVertical);
        dfs(row + 1, col + 1, node.right, map, minVertical);
    }

    /*********************************** Pair class *********************************/
    static class Pair{
        int row, col;
        TreeNode node;
        public Pair(int row, int col, TreeNode node){
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    /*********************************** Tree Node class *********************************/
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int data) {
            this.val = data;
        }
    }
}
