package Arrays.MaxSumOfRectangleNoLargerThanK;
import java.util.TreeSet;

// https://www.youtube.com/watch?v=IDv9yvQN3Uc
// https://www.youtube.com/watch?v=VsBXWpBbuig

// If rows are larger than columns, then a if-else condition can be added
// And the problem can be done using column wise sum

class MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;        
        int column = matrix[0].length;
        int[] dp = new int[row];
        int maxSum = Integer.MIN_VALUE;
        
        for (int i=0; i<column; i++){
            for (int a = 0; a < row; a++)
                dp[a] = 0;

            for (int j=i; j<column; j++){
                for (int a = 0; a < row; a++)
                    dp[a] += matrix[a][j];
                
                maxSum = Math.max(maxSum, maxSumNoGreaterThanK_1D(dp, row, k));        
            }
        }
        return maxSum;

    }
             
    private int maxSumNoGreaterThanK_1D(int[] arr, int n, int k){
        int maxSumNoGreaterThanK = Integer.MIN_VALUE;
        int prefixSum = 0;

        // https://www.javatpoint.com/java-treeset
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        
        for (int i=0; i<n; i++){
            prefixSum += arr[i];
            
            Integer prefixSum_previous = set.ceiling(prefixSum - k);
            
            if (prefixSum_previous != null)
                maxSumNoGreaterThanK = Math.max(maxSumNoGreaterThanK, prefixSum - prefixSum_previous);
            
            set.add(prefixSum);
        }
        return maxSumNoGreaterThanK;
    }
}