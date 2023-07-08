package Graphs.JumpGame_VII;
import java.util.ArrayDeque;
import java.util.Queue;

// https://leetcode.com/problems/jump-game-vii/
// https://leetcode.com/problems/jump-game-vii/solutions/1236272/java-bfs-detailed-analysis-o-n-bfs-solution/

public class JumpGame_VII {
    /*************************************** DFS Solution **************************************
     * Intuition:
        * Treat this as a graph problem
        * neighbour nodes of current node index 'i' are  nodes with index in range [i + minJump, min(i + maxJump, n - 1)]
        * We can visit these neighbour nodes only if their value is '0'
     * This solution gives TLE due to constraints

     * Time Complexity: O(n^2)
        * One DP State of size n
        * for loop inside each call
     * Space Complexity: O(n)
        * Visited_Array + Stack_space
     * */
    public boolean canReach__dfs(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] visited = new boolean[n];
        return f(0, minJump, maxJump, s.length(), s, visited);
    }
    private boolean f(int i, int minJump, int maxJump, int n, String s, boolean[] visited){
        if (i == n - 1)
            return true;

        visited[i] = true;
        for (int j = i + minJump; j <= Math.min(i + maxJump, n - 1); j++){
            if (s.charAt(j) == '0'  &&  !visited[j]) {
                if (f(j, minJump, maxJump, n, s, visited))
                    return true;
            }
        }
        return false;
    }

    /************************************** BFS Solution **************************************
     * BFS Version of above DFS solution
     * This also gives TLE

     * Time Complexity: O(n^2)
        * One DP State of size n
        * for loop inside each call
     * Space Complexity: O(n)
        * BFS_Queue + visited_array
     */
    public boolean canReach__bfs(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()){
            int i = queue.remove();
            if (i == n - 1)
                return true;

            for (int j = i + minJump; j <= Math.min(i + maxJump, n - 1); j++){
                if (s.charAt(j) == '0'  &&  !visited[j]) {
                    queue.add(j);
                    visited[j] = true;
                }
            }
        }
        return false;
    }


    /************************************** Optimized BFS *************************************
     * A slight modification to above BFS can make the above BFS solution to run in liner time i.e, O(n)
     * For intuition, read the article

     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        if (s.charAt(n - 1) == '1')
            return false;

        int maxReached = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(0);

        while (!queue.isEmpty()){
            int i = queue.remove();

            for (int j = Math.max(i + minJump, maxReached + 1); j <= Math.min(i + maxJump, n - 1); j++){
                if (s.charAt(j) == '0'){
                    if (j == n - 1)
                        return true;
                    queue.add(j);
                }
            }
            maxReached = i + maxJump;
        }
        return false;
    }
}
