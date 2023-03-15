package Graphs.MinimumMultiplicationsToReachEnd;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

// PRE_REQUISITE: "Shortest Path in Undirected Graph with Unit Weights"
// https://youtu.be/_BvEJ3VIDWw
// https://practice.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1
// https://takeuforward.org/graph/g-39-minimum-multiplications-to-reach-end/

public class MinimumMultiplicationsToReachEnd {
    /*********************************** Efficient Solution *******************************************
     * Intuitions:
        * Nodes can be considered as the numbers from 0 to 1e5
        * Edges are the array elements, which multiplies with a node to convert to other node.
            Every edge will convert a node to another node, and will have an edge value of 1.
        * Problem now becomes "Minimum distance required to reach from start node to dest node",
            the neighbours of a node are determined by multiplication of array elements.

     * About DFS: DFS can't be applied here, because we don't know till what depth we need to go.

     * Time Complexity: O(100000 * n)
        * Very hypothetical time complexity
        * Can't be exactly determined
     * Space Complexity: O(100000)
     */
    public int minimumMultiplications(int[] arr, int start, int end) {
        final int MOD = (int) 1e5;

        // Shortest Path Array
        int[] minMultiply = new int[MOD];
        Arrays.fill(minMultiply, Integer.MAX_VALUE);
        minMultiply[start] = 0;

        // Queue
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.node == end)
                return node.steps;

            for (int edge : arr) {
                int neighbour = (node.node * edge) % MOD;
                if (minMultiply[neighbour] > node.steps + 1) {
                    minMultiply[neighbour] = node.steps + 1;
                    queue.add(new Node(neighbour, node.steps + 1));
                }
            }
        }
        return -1;
    }


    static class Node {
        int node, steps;
        public Node(int node, int steps) {
            this.node = node;
            this.steps = steps;
        }
    }
}
