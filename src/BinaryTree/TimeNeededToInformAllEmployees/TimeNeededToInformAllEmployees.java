package BinaryTree.TimeNeededToInformAllEmployees;
import java.util.ArrayList;

// https://leetcode.com/problems/time-needed-to-inform-all-employees/description/

public class TimeNeededToInformAllEmployees {
    /********************************* DFS Solution *******************************************
     * Intuition:
        * Since is it guaranteed that each employee will have only one manager, so the
            resulting graph will have a n-ary "Tree structure".

     * Time Complexity: O(n)
        * Adjacency List Generation -> O(n)
        * N-ary Tree traversal -> O(n)
     * Space Complexity: O(n)
        * Since there is one manager for each employee, the adjList will be O(n)
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        ArrayList<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adjList[i] = new ArrayList<>();

        // Construct Adjacency list
        for (int node = 0; node < n; node++){
            int parent = manager[node];
            if (parent == -1)
                continue;
            adjList[parent].add(node);
        }

        // since we are doing a tree traversal, we don't need a visited array for DFS
        return dfs(headID, adjList, informTime);
    }

    // Calculate the maximum sum of edge(or node) weights along a path from src to leaf node
    private int dfs(int node, ArrayList<Integer>[] adjList, int[] informTime){
        int time = 0;

        for (int neighbour : adjList[node]){
            time = Math.max(time, informTime[node] + dfs(neighbour, adjList, informTime));
        }
        return time;
    }
}
