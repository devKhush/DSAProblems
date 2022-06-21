// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Driver_class
{
    public static void main(String args[])
    {
        
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            int grid[][] = new int[9][9];
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                grid[i][j] = sc.nextInt();
            }
            
            Solution ob = new Solution();
            
            if(ob.SolveSudoku(grid) == true)
                ob.printGrid(grid);
            else
                System.out.print("NO solution exists");
            System.out.println();
            
        }
    }
}



// } Driver Code Ends


//User function Template for Java

class Solution{
    public static boolean SolveSudoku(int grid[][]){
        return fillSudoKuBoard(0, 0, grid);    
    }
    
    public static boolean fillSudoKuBoard(int row, int column, int[][] board){
        if (row == 8 && column == 9)
            return true;

        if (column == 9){
            row++;
            column = 0;
        }        
        
        if (board[row][column] != 0)
            return fillSudoKuBoard(row, column + 1, board);
        
        for (int number = 1; number <= 9; number++){
            if (isValidPositionForNumber(row, column, board, number)){
                board[row][column] = number;
                
                if (fillSudoKuBoard(row, column + 1, board))
                    return true;
                
                board[row][column] = 0;
            }
        }
        return false;
    }
    
    public static boolean isValidPositionForNumber(int row, int column, int[][] board, int number){
        for (int i = 0; i < 9; i++){
            if (board[row][i] == number)
                return false;
                
            if (board[i][column] == number)
                return false;
            
            if (board[3*(row/3) + i/3][3*(column/3) + i%3] == number)
                return false;
        }
        return true;
    }
    
    public static void printGrid (int grid[][]){
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                System.out.print(grid[i][j] + " ");
    }
}