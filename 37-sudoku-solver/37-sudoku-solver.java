class Solution {
    public void solveSudoku(char[][] board) {
        dfs(board);
    }

    private boolean dfs(char[][] board){
        for (int row = 0; row < 9; row++){
            for (int column = 0; column < 9; column++){
                if (board[row][column] == '.') {

                    for (char ch = '1'; ch <= '9'; ch++) {
                        if (isValidPositionForNumber(ch, row, column, board)) {
                            board[row][column] = ch;

                            if (dfs(board))
                                return true;
                            board[row][column] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        // This is the case when all the rows & columns in the sudoku-puzzle are filled
        // then return true, bcoz all the positions in the board has been filled
        return true;
    }

    private boolean isValidPositionForNumber(char ch, int row, int column, char[][] board){
        for (int i = 0; i < 9; i++){
            if (board[row][i] == ch)
                return false;

            if (board[i][column] == ch)
                return false;

            if (board[3 * (row/3) + (i/3)][3 * (column/3) + (i%3)] == ch)
                return false;
        }
        return true;
    }
}