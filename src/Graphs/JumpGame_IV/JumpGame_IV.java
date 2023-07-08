package Graphs.JumpGame_IV;
import java.util.*;

// https://leetcode.com/problems/jump-game-iv/description/

public class JumpGame_IV {
    /*************************** Inefficient DFS Solution ******************************************
     * This DFS Solution will try out all the possible paths to the last index, and will return the
        path length with shortest distance.
     * Time Complexity: O(n*n)
        * Trying out all the paths takes O(E*E), E = edges
     * Space Complexity: O(n)
        * Visited array, Indices hashmap and Stack space takes O(n)  space
     * */
    public int minJumps__dfs(int[] arr) {
        int n = arr.length;
        boolean[] visited = new boolean[n];

        // Stores all indices of the indices in a map
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            ArrayList<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        return f(0, arr, map, visited);
    }

    private int f(int i, int[] arr, HashMap<Integer, ArrayList<Integer>> map, boolean[] visited){
        if (i == arr.length - 1)
            return 0;

        // We are trying out all the paths, so need to make visited[i] to true and false
        visited[i] = true;
        int next = i + 1 < arr.length  &&  !visited[i + 1] ? 1 + f(i + 1, arr, map, visited) : (int)1e9;
        int prev = i - 1 >= 0  &&  !visited[i - 1]? 1 + f(i - 1, arr, map, visited) : (int)1e9;
        int same = (int)1e9;
        for (int j : map.get(arr[i])){
            if (!visited[j])
                same = Math.min(same, 1 + f(j, arr, map, visited));
        }
        visited[i] = false;
        return Math.min(same, Math.min(next, prev));
    }


    /************************************** Efficient BFS Solution ***********************************
     * Intuition:
        * In DFS solution, we are trying out all the paths and choosing a path with minimum distance.
        * BFS is the only algorithm meant for trying out all the paths and choosing a path with
            minimum distance and not DFS.
        * BFS is a shortest path algorithm

     * Time Complexity: O(n)
        * since we will visit every node at most once.
     * Space Complexity: O(n)
        * Visited array, Indices hashmap and Queue takes O(n)  space
     */
    public int minJumps_(int[] arr) {
        int n = arr.length;

        // Stores all indices of the indices in a map
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++){
            ArrayList<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }

        // BFS Distance
        int steps = 0;

        // Normal Visited array
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;

        // To avoid traversing all the indices of same elements again and again, we need to maintain
        // another set only to keep track for duplicate elements visited
        HashSet<Integer> uniqueVisited = new HashSet<>();

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int i = queue.remove();
                if (i == n - 1)
                    return steps;

                if (i + 1 < arr.length  &&  !visited[i + 1]){       // Add next element
                    queue.add(i + 1);
                    visited[i + 1] = true;
                }
                if (i - 1 >= 0  &&  !visited[i - 1]){               // Add previous element
                    queue.add(i - 1);
                    visited[i - 1] = true;
                }
                // If all the duplicates of this elements has already been visited, don't visit them again
                if (uniqueVisited.contains(arr[i]))
                    continue;
                uniqueVisited.add(arr[i]);
                for (int j : map.get(arr[i])){
                    if (!visited[j]){
                        queue.add(j);
                        visited[j] = true;
                    }
                }

                // ALTER:
                // Instead of maintaining a separate set for checking duplicated, we can simply clear
                // the list of indices of current elements, so that we won't visit them again. This will also work
                // map.get(arr[i]).clear();
            }
            steps++;
        }
        return -1;
    }
}
