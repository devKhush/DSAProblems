//{ Driver Code Starts
// Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

// Position this line where user code will be pasted.

class GFG {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            int start = sc.nextInt();
            int end = sc.nextInt();

            Solution ob = new Solution();
            int ans = ob.minimumMultiplications(a, start, end);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    public int minimumMultiplications(int[] arr, int start, int end) {
        final int MOD = (int) 1e5;

        int[] minMultipy = new int[MOD];
        Arrays.fill(minMultipy, Integer.MAX_VALUE);
        minMultipy[start] = 0;

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.node == end)
                return node.steps;

            for (int edge : arr) {
                int neighbour = (node.node * edge) % MOD;
                if (minMultipy[neighbour] > node.steps + 1) {
                    minMultipy[neighbour] = node.steps + 1;
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
