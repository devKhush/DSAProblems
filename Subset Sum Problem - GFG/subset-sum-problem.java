// { Driver Code Starts
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
            int N = Integer.parseInt(read.readLine());
            String input_line[] = read.readLine().trim().split("\\s+");
            int arr[]= new int[N];
            for(int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(input_line[i]);
            int sum = Integer.parseInt(read.readLine());

            Solution ob = new Solution();
            if(ob.isSubsetSum(N, arr, sum))
            System.out.println(1);
            else
            System.out.println(0);

            
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    static Boolean isSubsetSum(int n, int arr[], int targetSum){
         n = arr.length;

        // dp[index][targetSum] means whether at index 'index' the subset whose sum is 'targetSum' is
        // possible or not
        boolean[][] dp = new boolean[n][targetSum + 1];

        // Base case when target == 0
        for (int index = 0; index < n; index++)
            dp[index][0] = true;

        // Base case when index == 0
        for (int target = 0; target <= targetSum; target++)
            dp[0][target] = (arr[0] == target);
        // Shorthand base case for index == 0, it means on index 0 can we format a target arr[0]
        if (arr[0] <= targetSum)
            dp[0][arr[0]] = true;

        // Other cases
        for (int index = 1; index < n; index++) {
            for (int target = 1; target <= targetSum; target++) {

                boolean subsetMadeByPickingCurrElement = false;
                if (arr[index] <= target)
                    subsetMadeByPickingCurrElement = dp[index - 1][target - arr[index]];

                boolean subsetMadeByNotPickingCurrElement = dp[index-1][target];

                dp[index][target] = subsetMadeByNotPickingCurrElement || subsetMadeByPickingCurrElement;
            }
        }

        return dp[n-1][targetSum];
    }
    
    static boolean containsSubsetSumEqualsK_Memoization(int index, int target, int[] arr, int[][] dp){
        if (target == 0)
            return true;
            
        if (index == 0)
            return arr[0] == target;
            
        if (dp[index][target] != -1)
            return dp[index][target] == 1 ? true : false;
            
        boolean foundByPickingElement = false;
        if (arr[index] <= target)
            foundByPickingElement = containsSubsetSumEqualsK_Memoization(index-1, target - arr[index], arr, dp);
        if (foundByPickingElement){
            dp[index][target] = 1;
            return true;
        }
        
        boolean foundByNotPickingElement = containsSubsetSumEqualsK_Memoization(index-1, target, arr, dp);
        
        dp[index][target] = foundByNotPickingElement ? 1 : 0;
        
        return foundByNotPickingElement;
    }
    
    
}