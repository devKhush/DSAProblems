class Solution {
    // Basic Approach Made Efiicient using Hashing
    public List<List<String>> solveNQueens(int n) {
        char[][] chessBoard = new char[n][n];
        for (char[] row : chessBoard)
            Arrays.fill(row, '.');

        boolean[] hasQueenOnHorizontalLeft = new boolean[n];
        boolean[] hasQueenOnUpperLeftDiagonal = new boolean[2*n - 1];
        boolean[] hasQueenOnLowerLeftDiagonal = new boolean[2*n - 1];
        List<List<String>> allValidQueenArrangements = new ArrayList<>();
        
        dfs(0, n, chessBoard, allValidQueenArrangements, hasQueenOnHorizontalLeft, hasQueenOnUpperLeftDiagonal, hasQueenOnLowerLeftDiagonal);
        return allValidQueenArrangements;
    }

    private void dfs(int column, int n, char[][] board, List<List<String>> allQueenArrangements,
                     boolean[] hasQueenOnHorizontalLeft, boolean[] hasQueenOnUpperLeftDiagonal,
                     boolean[] hasQueenOnLowerLeftDiagonal){

        if (column == n){
            ArrayList<String> list = new ArrayList<>();
            for (char[] row : board)
                list.add(new String(row));
            allQueenArrangements.add(list);
            return;    
        }
        
        for (int row = 0; row < n; row++){

            if (!hasQueenOnHorizontalLeft[row]  && !hasQueenOnLowerLeftDiagonal[row + column] &&
                    !hasQueenOnUpperLeftDiagonal[n - 1 + column - row]){
                hasQueenOnHorizontalLeft[row] = true;
                hasQueenOnLowerLeftDiagonal[column + row] = true;
                hasQueenOnUpperLeftDiagonal[n - 1 + column - row] = true;
                
                board[row][column] = 'Q';
                dfs(column +1 ,n, board, allQueenArrangements, hasQueenOnHorizontalLeft, hasQueenOnUpperLeftDiagonal, hasQueenOnLowerLeftDiagonal);
                board[row][column] = '.';

                hasQueenOnUpperLeftDiagonal[n - 1 + column - row] = false;
                hasQueenOnLowerLeftDiagonal[column + row] = false;
                hasQueenOnHorizontalLeft[row] = false;
            }
        }
    }
    
    
   /* // Basic Approach **********************************************************
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
    */
}