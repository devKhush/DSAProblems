package Graphs.CloneGraph;
import java.util.*;

// https://leetcode.com/problems/clone-graph/description/

public class CloneGraph {
    /********************************* DFS Solution ************************************************
     * Intuition: Make graph while doing DFS Traversal
     * Time Complexity: O(V + 2E)
        * Plain DFS
     * Space Complexity: O(V)
        * Visited Array
     */
    public Node cloneGraph_dfs(Node node) {
        if (node == null)
            return null;
        HashMap<Integer, Node> visited = new HashMap<>();
        return dfs(node, visited);
    }

    private Node dfs(Node node, HashMap<Integer, Node> visited){
        Node u = new Node(node.val);
        visited.put(u.val, u);

        for (Node neighbour : node.neighbors){
            if (!visited.containsKey(neighbour.val)){
                dfs(neighbour, visited);
            }
            Node v = visited.get(neighbour.val);
            u.neighbors.add(v);
        }
        return u;
    }

    /****************************************** BFS Solution ***************************************
     * Time Complexity: O(V + 2E)
     * Space Complexity: O(2*V) ~ O(V)
     */
    public Node cloneGraph_bfs(Node src) {
        if (src == null)
            return null;

        HashMap<Integer, Node> visited = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(src);
        visited.put(src.val, new Node(src.val));

        while (!queue.isEmpty()){
            Node node = queue.remove();
            Node u = visited.get(node.val);

            for (Node neighbour : node.neighbors){
                if (!visited.containsKey(neighbour.val)){
                    visited.put(neighbour.val, new Node(neighbour.val));
                    queue.add(neighbour);
                }
                Node v = visited.get(neighbour.val);
                u.neighbors.add(v);
            }
        }
        return visited.get(src.val);
    }


    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
    }
}
