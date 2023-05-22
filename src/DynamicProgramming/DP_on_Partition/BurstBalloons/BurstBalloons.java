package DynamicProgramming.DP_on_Partition.BurstBalloons;

// https://youtu.be/Yz4LlDSlkns
// https://takeuforward.org/data-structure/burst-balloons-partition-dp-dp-51/
// https://leetcode.com/problems/burst-balloons/

public class BurstBalloons {
    /****************************************** Recursion *******************************************
     * Intuition:
        * Try all possible partitions of bursting balloons, but the left and right sub-problems won't be
            independent of each other. So, try to solve the problem in opposite way, i.e, try to add
            balloons in th set of all empty balloons. In this case, left and right sub-problems will be
            independent of each other.
        * Watch video for detailed intuition

     * Time Complexity: Exponential
     * Space Complexity: O(n)
        * Because in each call partitions will get smaller, so the max recursion stack space is O(n)
     */
    public int maxCoins_rec(int[] nums) {
        int n = nums.length;

        // Add 1 to the front and end of the array
        int[] balloons = new int[n + 2];
        balloons[0] = balloons[n + 1] = 1;
        System.arraycopy(nums, 0, balloons, 1, n);

        // Start with entire array/block
        return f(1, n, balloons);
    }
    private int f(int i, int j, int[] nums){
        if (i > j) return 0;

        // To make the left and right partition independent of each other, we start from the bottom
        // Instead of bursting balloons, we try to add balloons from the whole bursted balloons set
        int maxCoins = 0;
        for (int k = i; k <= j; k++){
            int coins = (nums[i-1]*nums[k]*nums[j+1]) + f(i, k-1, nums) + f(k+1, j, nums);
            maxCoins = Math.max(maxCoins, coins);
        }
        return maxCoins;
    }

    /**************************************** Memoization ********************************************
     * Memoization of above recursive solution

     * Time Complexity: O(n*n*n)
        * There are two changing states, i & j.
        * Another loop for k will run for each states on average n time
     * Space Complexity: O(n*n) + O(n)
        * DP Array + Recursion Stack space
     */
    public int maxCoins_memo(int[] nums) {
        int n = nums.length;

        // Add 1 to the front and end of the array
        int[] balloons = new int[n + 2];
        balloons[0] = balloons[n + 1] = 1;
        System.arraycopy(nums, 0, balloons, 1, n);

        // DP Array
        Integer[][] dp = new Integer[n + 1][n + 1];

        // Start with entire array/block
        return f(1, n, balloons, dp);
    }
    private int f(int i, int j, int[] nums, Integer[][] dp ){
        if (i > j) return 0;
        if (dp[i][j] != null)
            return dp[i][j];

        // To make the left and right partition independent of each other, we start from the bottom
        // Instead of bursting balloons, we try to add balloons from the whole bursted balloons set
        int maxCoins = 0;
        for (int k = i; k <= j; k++){
            int coins = (nums[i-1]*nums[k]*nums[j+1]) + f(i, k-1, nums, dp) + f(k+1, j, nums, dp);
            maxCoins = Math.max(maxCoins, coins);
        }
        return dp[i][j] = maxCoins;
    }

    /**************************************** Tabulation ********************************************
     * Tabulation of above memoization

     * Time Complexity: O(n*n*n)
         * Two changing states: i & j
         * One loop inside for each state
     * Space Complexity: O(n*n)
         * DP Array
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;

        // Add 1 to the front and end of the array
        int[] balloons = new int[n + 2];
        balloons[0] = balloons[n + 1] = 1;
        System.arraycopy(nums, 0, balloons, 1, n);

        //  DP Array: No base case for i>j (0 by-default)
        int[][] dp = new int[n+1][n+1];

        // Changing states inside loops in opposite directions
        for (int i = n; i >= 1; i--){
            for (int j = i; j <= n; j++){
                int maxCoins = 0;
                for (int k = i; k <= j; k++){
                    int coins = (balloons[i-1]*balloons[k]*balloons[j+1]) + dp[i][k-1] + dp[k+1][j];
                    maxCoins = Math.max(maxCoins, coins);
                }
                dp[i][j] = maxCoins;
            }
        }
        return dp[1][n];
    }
}
