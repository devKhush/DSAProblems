package SurroundedRegions;

import java.util.Arrays;

// https://leetcode.com/problems/surrounded-regions/

public class SurroundedRegionsXO {
    public boolean isBoundary(char[][] board, int i, int j){
        return i<=0 || j<=0 || i >= board.length-1 || j >= board[i].length-1;
    }

    public boolean hasOasBoundary(char[][] board, int i, int j){
        boolean top = isBoundary(board, i - 1, j) && board[i - 1][j] == 'O';
        boolean bottom = isBoundary(board, i + 1, j) && board[i + 1][j] == 'O';
        boolean left = isBoundary(board, i, j - 1) && board[i][j - 1] == 'O';
        boolean right = isBoundary(board, i, j + 1) && board[i][j + 1] == 'O';
        return top || bottom || left || right;
    }

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]=='O' && !isBoundary(board,i,j) && !hasOasBoundary(board,i,j)){
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{{'X','X','X','X'}, {'X','O','O','X'}, {'X','X','O','X'}, {'X','O','X','X'}};
        // board = new char[][]{{'X'}};

        // board = new char[][]{{'X','X','O','X'}, {'X','O','O','X'}, {'X','X','O','X'}, {'X','O','X','X'}};
        // board = new char[][]{{'X','X','O','X'}, {'X','O','O','X'}, {'X','X','O','X'}, {'X','O','O','X'}};

         board = new char[][] {{'O','X','X','O','X'},
                               {'X','O','O','X','O'},
                               {'X','O','X','O','X'},
                               {'O','X','O','O','O'},
                               {'X','X','O','X','O' }};
        new SurroundedRegionsXO().solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}
