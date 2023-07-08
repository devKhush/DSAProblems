package Graphs.JumpGame_II;
import java.util.ArrayDeque;
import java.util.Queue;

// Pre-requisite: DFS and BFS of "Jump Game - I"
// https://leetcode.com/problems/jump-game-ii/description/

public class JumpGame_II_BFS {
    /******************************** BFS: Shortest Path (Brute Force) ***********************************
     * Intuition:
        * DP Solution has a quadratic time
        * We need to find the minimum distance from index 0 to n-1
        * From a node/index 'i' we can reach its neighbors within a distance of "i + nums[i]"
        * Use BFS Shortest Path algorithm

     * Time Complexity: O(n * max)
        * O(n) for BFS
        * max => max value in array
        * one loop of size max
     * Space Complexity: O(n)
        * Visited Array + Queue
     */
    public int jump__bfs(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int jumps = 0;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int i = queue.remove();

                for (int j = i + 1; j <= Math.min(i + nums[i], n - 1); j++){
                    if (!visited[j]){
                        if (j == n - 1)
                            return jumps + 1;
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }
            jumps++;
        }
        return -1;
    }


    /********************************** Efficient BFS ***********************************
     * Intuition:
        * Slight modification in above BFS can make it run in linear time
        * Main intuition is that we won't loop over the indices that we have visited in past

     * Time Complexity: O(n)
        * Linear time BFS
     * Space Complexity: O(n)
        * Queue
     */
    public int jumpGame_II(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int jumps = 0;
        int maxReached = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int i = queue.remove();

                for (int j = maxReached + 1; j <= Math.min(i + nums[i], n - 1); j++){
                    if (j == n - 1)
                        return jumps + 1;
                    queue.add(j);
                    maxReached = j;
                }
            }
            jumps++;
        }
        return -1;
    }

}
