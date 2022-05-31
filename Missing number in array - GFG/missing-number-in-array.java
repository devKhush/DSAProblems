// { Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] str = br.readLine().trim().split(" ");
            int[] array = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                array[i] = Integer.parseInt(str[i]);
            }
            Solution sln = new Solution();
            System.out.println(sln.MissingNumber(array, n));
        }
    }
}// } Driver Code Ends


// User function Template for Java

class Solution {
    int MissingNumber(int array[], int n) {
        
        // Let's calculate the sum from 1 to N using AP series
        int sumFromoneToN = n*(n+1)/2;
        int arraySum = 0;
        
        // Actual array Sum that has a missing number in range [1, N]
        for (int element : array) 
            arraySum+= element;
            
        // That element willn't contribute in arraySum
        // so difference is the answer
        return sumFromoneToN - arraySum;
    }
}