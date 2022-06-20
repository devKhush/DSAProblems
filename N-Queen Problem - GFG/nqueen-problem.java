// { Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;
import java.lang.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0) {
            int n = Integer.parseInt(in.readLine());
            
            Solution ob = new Solution();
            ArrayList<ArrayList<Integer>> ans = ob.nQueen(n);
            if(ans.size() == 0)
                System.out.println("-1");
            else {
                for(int i = 0;i < ans.size();i++){
                    System.out.print("[");
                    for(int j = 0;j < ans.get(i).size();j++)
                        System.out.print(ans.get(i).get(j) + " ");
                    System.out.print("] ");
                }
                System.out.println();
            }
        }
    }
}// } Driver Code Ends


// User function Template for Java

class Solution{
    static ArrayList<ArrayList<Integer>> nQueen(int n) {
        int[][] board = new int[n][n];
            
        ArrayList<ArrayList<Integer>> allQueenPositions = new ArrayList<>();
        
        dfs(0, n, board, allQueenPositions);
        return allQueenPositions;
    }
    
    static void dfs(int column, int n, int[][] board, ArrayList<ArrayList<Integer>> allQueenPositions){
        if (column == n){
            ArrayList<Integer> list = new ArrayList<>();
            
            for (int j = 0; j < n; j++)
                for (int i = 0; i < n; i++)
                    if (board[i][j] == 1)
                        list.add(i + 1);
                        
            allQueenPositions.add(list);
            return;
        }
        
        for (int row = 0; row < n; row++){
            if (isQueenSafeAndCanPlace(row, column, board, n)){
                board[row][column] = 1;
                dfs(column + 1, n, board, allQueenPositions);
                board[row][column] = 0;
            }
        }
    }
    
    static boolean isQueenSafeAndCanPlace(int rowNumber, int columnNumber, int[][] board, int n){
        for (int row = rowNumber, column = columnNumber; row >= 0 && column >= 0; row--, column--)
            if (board[row][column] == 1)
                return false;
        
        for (int row = rowNumber, column = columnNumber; column >= 0; column--)
            if (board[row][column] == 1)
                return false;
        
        for (int row = rowNumber, column = columnNumber; row < n && column >= 0; row++, column--)
            if (board[row][column] == 1)
                return false;
        return true;
    }
}





