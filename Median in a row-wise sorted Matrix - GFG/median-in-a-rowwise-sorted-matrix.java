// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;


class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String S[] = read.readLine().split(" ");
            int R = Integer.parseInt(S[0]);
            int C = Integer.parseInt(S[1]);
            String line[] = read.readLine().trim().split("\\s+");
            int matrix[][] = new int[R][C];
            int c = 0;
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    matrix[i][j] = Integer.parseInt(line[c++]);
                }
            }
            Solution ob = new Solution();
            int ans = ob.median(matrix, R, C);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution {
    int median(int matrix[][], int r, int c) {
        int low = 1, high = (int)1e8;
        
        while (low <= high){
            int mid = (low + high) >> 1;
            
            int count = 0;
            for (int i = 0; i < r; i++)
                count += countOfNumberLessThanOrEqualTo(matrix[i], mid, c);
            if (count <= (r * c)/2)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }
    
    
    private int countOfNumberLessThanOrEqualTo(int[] arr, int num, int n){
        int low = 0, high = n - 1;

        while (low <= high){
            int mid = low + (high - low)/2;

            if (arr[mid] <= num)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }
}