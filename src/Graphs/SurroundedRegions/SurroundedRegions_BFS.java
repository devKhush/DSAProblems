package Graphs.SurroundedRegions;
import java.util.ArrayDeque;
import java.util.Queue;

// https://youtu.be/BtdgAys4yMk
// https://leetcode.com/problems/surrounded-regions/description/
// https://takeuforward.org/graph/surrounded-regions-replace-os-with-xs/
// https://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/

public class SurroundedRegions_BFS {
    /*************************************** BFS Solution *************************************8
     * Intuition: Set of O's cells directly or indirectly connected to boundary O's cells
            can't be converted to X's.

     * Time Complexity: O(m) + O(n) + O(4*mn) + O(mn)  ~  O(mn)
     * Space Complexity: O(mn) + O(mn)  ~  O(mn)
        * O(mn) for BFS queue
        * O(mn) for visited array
     */
    public void surroundedRegions(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] di = { 1, -1, 0, 0 };
        int[] dj = { 0, 0, 1, -1 };
        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                queue.add(new int[]{i, 0});
                visited[i][0] = true;
            }
            if (board[i][n - 1] == 'O') {
                queue.add(new int[]{i, n - 1});
                visited[i][n-1] = true;
            }
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                queue.add(new int[]{0, j});
                visited[0][j] = true;
            }
            if (board[m - 1][j] == 'O') {
                queue.add(new int[]{m - 1, j});
                visited[m-1][j] = true;
            }
        }

        while (!queue.isEmpty()) {
            int i = queue.peek()[0];
            int j = queue.remove()[1];

            for (int a = 0; a < 4; a++) {
                int newI = i + di[a];
                int newJ = j + dj[a];
                if (newI >= 0 && newJ >= 0 && newI < m && newJ < n && board[newI][newJ]=='O' && !visited[newI][newJ]) {
                    queue.add(new int[]{newI, newJ});
                    visited[newI][newJ] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O'  &&  !visited[i][j])
                    board[i][j] = 'X';
            }
        }
    }
}
