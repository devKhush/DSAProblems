// { Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int N, M, x, y;
            String S[] = read.readLine().split(" ");
            N = Integer.parseInt(S[0]);
            M = Integer.parseInt(S[1]);
            int a[][] = new int[N][M];
            for (int i = 0; i < N; i++) {
                String s[] = read.readLine().split(" ");
                for (int j = 0; j < M; j++) a[i][j] = Integer.parseInt(s[j]);
            }
            Solution ob = new Solution();
            System.out.println(ob.maximumSumRectangle(N, M, a));
        }
    }
}// } Driver Code Ends


// User function Template for Java

class Solution {
    int maximumSumRectangle(int R, int C, int arr[][]) {
        int maxSum = Integer.MIN_VALUE;
        int[] temp = new int[C]; 
        
        for (int i=0; i<R; i++){
            for (int k=i; k<R; k++){
                
                for (int j=0; j<C; j++)
                    temp[j] += arr[k][j];
                
                maxSum = Math.max(maxSum, kadane1D(temp, C));
            }
            
            for (int j=0; j<C; j++)
                temp[j] = 0;
        }
        return maxSum;
    }
 
    private int kadane1D(int[] arr, int n){
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int val: arr){
            sum += val;
            
            if (sum > maxSum)
                maxSum = sum;
                
            if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }
}