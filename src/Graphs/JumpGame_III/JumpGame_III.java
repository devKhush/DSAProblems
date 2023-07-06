package Graphs.JumpGame_III;
import java.util.ArrayDeque;
import java.util.Queue;

// Pre-requisite: Jump Game I & II
// https://leetcode.com/problems/jump-game-iii/description/

public class JumpGame_III {
    /****************************************** DFS *********************************************
     * Intuition: Simple DFS
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public boolean canReach__DFS(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        return f(start, arr, visited);
    }

    private boolean f(int i, int[] arr, boolean[] visited){
        if (i < 0 || i >= arr.length)
            return false;
        if (visited[i])
            return false;
        if (arr[i] == 0)
            return true;

        visited[i] = true;
        return f(i + arr[i], arr, visited) || f(i - arr[i], arr, visited);
    }


    /*************************************** BFS ********************************************
     * Intuition: Simple BFS
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public boolean canReach__BFS(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()){
            int i = queue.remove();
            if (arr[i] == 0)
                return true;

            if (i - arr[i] >= 0  &&  !visited[i - arr[i]]){
                queue.add(i - arr[i]);
                visited[i - arr[i]] = true;
            }
            if (i + arr[i] < arr.length  &&  !visited[i + arr[i]]){
                queue.add(i + arr[i]);
                visited[i + arr[i]] = true;
            }
        }
        return false;
    }
}
