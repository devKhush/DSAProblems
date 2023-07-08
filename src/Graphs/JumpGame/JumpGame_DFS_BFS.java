package Graphs.JumpGame;
import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/jump-game/description/

public class JumpGame_DFS_BFS {
    /**************************************** DFS Solution **************************************
     * Intuition:
        * Simple DFS Solution
     * Time Complexity: O(n * max)
        * max => max. value present in array
        * One loop of size max
     * Space Complexity: O(n)
        * Stack space + Visited Array
     */
    public boolean canJump__dfs(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        return dfs(0, nums, visited);
    }
    private boolean dfs(int i, int[] nums, boolean[] visited){
        if (i == nums.length - 1)
            return true;

        visited[i] = true;
        for(int j = i + 1; j <= Math.min(i + nums[i], nums.length - 1); j++){
            if (!visited[j]  && dfs(j, nums, visited))
                return true;
        }
        return false;
    }

    /************************************* BFS Solution ***************************************
     * Intuition:
        * Simple BFS version of above DFS
     * Time Complexity: O(n * max)
        * max => max. value present in array
        * One loop of size max
     * Space Complexity: O(n)
        * Queue + Visited Array
     */
    public boolean canJump__bfs(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return true;

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()){
            int i = queue.remove();

            for (int j = i + 1; j <= Math.min(i + nums[i], n - 1); j++){
                if (!visited[j]){
                    if (j == n - 1)
                        return true;
                    queue.add(j);
                    visited[j] = true;
                }
            }
        }
        return false;
    }


    /************************************* Optimized BFS Solution ***************************************
     * Intuition:
        * Slight modification in above BFS can make it run in linear time
        * Main intuition is that we won't loop over the indices that we have visited in past

     * Time Complexity: O(n)
        * Linear time BFS
     * Space Complexity: O(n)
        * Queue
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return true;

        int maxReached = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        while (!queue.isEmpty()){
            int i = queue.remove();

            for (int j = maxReached + 1; j <= Math.min(i + nums[i], n - 1); j++){
                if (j == n - 1)
                    return true;
                queue.add(j);
                maxReached = j;
            }
        }
        return false;
    }
}
