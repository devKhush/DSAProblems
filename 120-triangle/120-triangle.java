class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        for (int j = 0; j < n; j++)
            dp[j] = triangle.get(n-1).get(j);

        for (int i= n-2; i >= 0; i--){
            int[] tempDP = new int[n];

            for (int j = i; j >= 0; j--) {
                int minPathByMovingDown = dp[j] + triangle.get(i).get(j);
                int minPathByMovingDownRight = dp[j+1] + triangle.get(i).get(j);

                tempDP[j] = Math.min(minPathByMovingDownRight, minPathByMovingDown);
            }
            dp = tempDP;
        }
        
        return dp[0];
        
    }
}