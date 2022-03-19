package Stacks.MinimumCostTreeFromLeafValues;

//https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
// https://www.youtube.com/watch?v=T6E74ypY_tU&t=1848s
// https://www.youtube.com/watch?v=pYs3qj42h3c

/*
class Solution {
    public int mctFromLeafValues(int[] arr) {
        return solve(arr, 0, arr.length-1).minSum;
    }
    
    public Pair solve(int[] arr, int low, int high){
        if (low == high)
            return new Pair(arr[low], 0);
        Pair root = new Pair(0, Integer.MAX_VALUE);
        
        for (int i=low; i<high; i++){
            Pair leftPair = solve(arr, low, i);
            Pair rightPair = solve(arr, i+1, high);
            
            int sum = leftPair.minSum + rightPair.minSum + rightPair.maxLeaf*leftPair.maxLeaf;
            if (sum < root.minSum){
                root.maxLeaf = Integer.max(rightPair.maxLeaf, leftPair.maxLeaf);
                root.minSum = sum;
            }
        }
        return root;
    }
}
*/

class Pair{
    int maxLeaf;
    int minSum;
    public Pair(int maxLeaf, int minSum){
        this.minSum = minSum;
        this.maxLeaf = maxLeaf;

    }
}
    
public class MinimumCostTreeFromLeafValues_UsingDP {
    public int mctFromLeafValues(int[] arr) {
        return solve(arr, 0, arr.length-1, new Pair[arr.length][arr.length]).minSum;
    }
    
    public Pair solve(int[] arr, int low, int high, Pair[][] dp){
        if (low == high)
            return new Pair(arr[low], 0);
        Pair root = new Pair(0, Integer.MAX_VALUE);
        
        if (dp[low][high]!=null)
            return dp[low][high];
        
        for (int i=low; i<high; i++){
            Pair leftPair = solve(arr, low, i, dp);
            Pair rightPair = solve(arr, i+1, high, dp);
            
            int sum = leftPair.minSum + rightPair.minSum + rightPair.maxLeaf*leftPair.maxLeaf;
            if (sum < root.minSum){
                root.maxLeaf = Integer.max(rightPair.maxLeaf, leftPair.maxLeaf);
                root.minSum = sum;
            }
        }
        
        dp[low][high] = root;
        return root;
    }
}
