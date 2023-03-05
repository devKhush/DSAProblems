package Graphs.SurroundedRegions;

// https://youtu.be/BtdgAys4yMk
// https://leetcode.com/problems/surrounded-regions/description/
// Check out this solution:
// https://takeuforward.org/graph/surrounded-regions-replace-os-with-xs/
// https://www.geeksforgeeks.org/given-matrix-o-x-replace-o-x-surrounded-x/

public class SurroundedRegions_DFS {
    /*************************************** DFS Solution *************************************8
     * Intuition: Set of O's cells directly or indirectly connected to boundary O's cells
            can't be converted to X's.

     * Time Complexity: O(m) + O(n) + O(4mn) + O(mn) ~  O(mn)
     * Space Complexity: O(mn)
        * O(mn) for only recursive stack space
     */
    public void surroundedRegions(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++){
            if (board[i][0] == 'O')
                dfs(i, 0, board);
            if (board[i][n - 1] == 'O')
                dfs(i, n - 1, board);
        }
        for (int j = 0; j < n; j++){
            if (board[0][j] == 'O')
                dfs(0, j, board);
            if (board[m - 1][j] == 'O')
                dfs(m - 1, j, board);
        }

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (board[i][j] == 'N')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    private void dfs(int i, int j, char[][] board){
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O')
            return;

        board[i][j] = 'N';
        dfs(i - 1, j, board);
        dfs(i, j + 1, board);
        dfs(i + 1, j, board);
        dfs(i, j - 1, board);
    }
}
