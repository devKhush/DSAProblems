class Solution {
   public List<List<String>> solveNQueens(int n) {
        char[][] chessBoard = new char[n][n];
        for (char[] row : chessBoard)
            Arrays.fill(row, '.');

        List<List<String>> allValidQueenArrangements = new ArrayList<>();

        dfs(0, n, chessBoard, allValidQueenArrangements);
        return allValidQueenArrangements;
    }

    public void dfs(int column, int n, char[][] board, List<List<String>> allQueenArrangements){
        if (column == n){
            ArrayList<String> list = new ArrayList<>();
            for (char[] row : board)
                list.add(new String(row));
            allQueenArrangements.add(list);
            return;
        }

        for (int row = 0; row < n; row++){
            if (isQueenSafe(row, column, n, board)){
                board[row][column] = 'Q';
                dfs(column + 1, n, board, allQueenArrangements);
                board[row][column] = '.';
            }
        }
    }

    public boolean isQueenSafe(int rowNumber, int columnNumber, int n, char[][] board){
        int row = rowNumber, column = columnNumber;

        while (row >= 0 && column >= 0){
            if (board[row][column] == 'Q')
                return false;
            row--;
            column--;
        }

        row = rowNumber;
        column = columnNumber;
        while (column >= 0){
            if (board[row][column] == 'Q')
                return false;
            column--;
        }

        row = rowNumber;
        column = columnNumber;
        while (row < n  && column >= 0){
            if (board[row][column] == 'Q')
                return false;
            row++;
            column--;
        }
        return true;
    }
}