package DynamicProgramming.DP_on_Arrays.JumpGame_VI;
import java.util.*;

// https://leetcode.com/problems/jump-game-vi/description/
// Great Explanation for optimization of DP Solution
// https://leetcode.com/problems/jump-game-vi/solutions/1260737/optimizations-from-brute-force-to-dynamic-programming-w-explanation/

public class JumpGame_VI {
    /************************************* DP: Memoization Gives TLE **********************************
     * Intuition:
        * Basic Recursion thinking
     * Time Complexity: O(n * k)
        * One DP state 'i' of size n
        * One for loop of size k in each call
     * Space Complexity: O(n)
        * DP Array + Stack Space
     * */
    public int maxResult__memo(int[] nums, int k) {
        Integer[] dp = new Integer[nums.length];
        return f(0, nums, k, dp);
    }

    private int f(int i, int[] nums, int k, Integer[] dp){
        if (i == nums.length - 1)
            return nums[i];
        if (dp[i] != null)
            return dp[i];

        int maxScore = -(int)1e9;
        for (int j = i + 1; j <= Math.min(i + k, nums.length - 1); j++){
            maxScore = nums[i] + Math.max(maxScore, f(j, nums, k, dp));
        }
        return dp[i] = maxScore;
    }

    /************************************* DP Tabulation: Gives TLE  **********************************
     * Time Complexity: O(n * k)
        * One DP state 'i' of size n
        * One for loop of size k in each call
     * Space Complexity: O(n)
        * DP Array
     * */
    public int maxResult_tabu(int[] nums, int k) {
        int n = nums.length;

        // DP Array & Base case
        int[] dp = new int[n];
        dp[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--){
            int maxScore = -(int)1e9;
            for (int j = i + 1; j <= Math.min(i + k, nums.length - 1); j++){
                maxScore = nums[i] + Math.max(maxScore, dp[j]);
            }
            dp[i] = maxScore;
        }
        return dp[0];
    }

    /*********************************** Most Optimized Tabulation ***********************************
     * Pre-requisite: Maximum of Window of size 'k'
     * Intuition:
        * In each DP state, we are finding maximum of next k DP state
        * We maintain a simple deque of size k, sorted in decreasing order to find the maximum of next
            'k' dp values

     * Time Complexity: O(n)
        * One DP state of size n
        * Add, remove and delete operation in Deque will take O(1) time
     * Space Complexity: O(n + k)
        * DP Array of size n
        * Deque of size 'k' to store maintain maximum of k elements
     */
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = nums[n - 1];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(n - 1);

        for (int i = n - 2; i >= 0; i--){
            // Remove the dp value which is outside window of size k
            if (deque.peekFirst() - i > k)
                deque.removeFirst();

            // Get the maximum of next 'k' dp value
            dp[i] = nums[i] + dp[deque.peekFirst()];

            // Maintain Deque in sorted order and add the element
            while (!deque.isEmpty()  &&  dp[deque.peekLast()] < dp[i])
                deque.removeLast();
            deque.add(i);
        }
        return dp[0];
    }


    /*************************************** Optimized Tabulation ************************************
     * Intuition:
        * In each DP state, we are finding maximum of next k DP state
        * So, we can maintain a TreeSet of size k to find the maximum in much lower log(k) time
        * Instead of TreeSet, maintain a TreeMap bcoz duplicate elements might be present

     * Time Complexity: O(n * log(k))
        * One DP state of size n
        * Add, remove and delete operation in TreeMap of size 'k' will take log(k) time
     * Space Complexity: O(n + k)
        * DP Array of size n
        * TreeMap of size 'k' to store maintain maximum of k elements
     */
    public int maxResult_TreeMap(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = nums[n - 1];

        // TreeMap stores (value, count) in decreasing order of keys (or dp values)
        TreeMap<Integer, Integer> maxMap = new TreeMap<>((a, b) -> b - a);
        maxMap.put(dp[n - 1], 1);

        for (int i = n - 2; i >= 0; i--){
            // Remove the key (dp value) which is outside  window of size k
            if (i + k < n - 1){
                maxMap.put(dp[i + k + 1], maxMap.get(dp[i + k + 1]) - 1);
                if (maxMap.get(dp[i + k + 1]) == 0)
                    maxMap.remove(dp[i + k + 1]);
            }
            // Get the maximum key (dp value)
            dp[i] = nums[i] + maxMap.firstKey();
            maxMap.put(dp[i], maxMap.getOrDefault(dp[i], 0) + 1);
        }
        return dp[0];
    }
}
