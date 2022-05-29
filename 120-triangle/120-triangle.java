class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
       int n = triangle.size();
        int[] dp = new int[n];
        
        // To calculate the min path from (0,0) to (i,j) we need previous top value (in previous row) 
        // and previous top-left diagonal value (also in previous row)
        // One DP array will not work here, as then how will we store the min path of current row 
        // We use one DP array 'dp' to get these required previous values.
        // And store the min path of current row values in another dp array 'tempDP'
        
        for (int i = 0; i < n; i++){
            int[] tempDP = new int[n];
            
            for (int j = 0; j <= i; j++){
                if (i==0 && j==0){
                    tempDP[0] = triangle.get(0).get(0);
                    continue;
                }
                
                int minPathByMovingUp = (j <= i-1) ? dp[j] + triangle.get(i).get(j) : Integer.MAX_VALUE/2;
                
                int minPathByMovingUpLeft = (j > 0) ? dp[j-1] + triangle.get(i).get(j) : Integer.MAX_VALUE/2;
                
                tempDP[j] = Math.min(minPathByMovingUpLeft, minPathByMovingUp);
            }
            dp = tempDP;
        }
        
        int minPathSum = Integer.MAX_VALUE;
        for (int pathSum : dp)
            minPathSum = Math.min(pathSum, minPathSum);
        
        return minPathSum;
        
    }
}