package BitManipulation.SingleNumber_II;
import java.util.HashMap;

// https://leetcode.com/problems/single-number-ii/description/
// https://youtu.be/7xJJ_42P_0U

public class SingleNumber_II {
    /*********************************** Brute HashMap Solution ********************************
     * Time Complexity: O(n) or O(n*log(n))
        * n*log(n) in worst case due to HashMap
     * Space Complexity: O(n)
     */
    public int singleNumber_map(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int x : nums){
            count.put(x, count.getOrDefault(x, 0) + 1);
        }
        for (int x : nums){
            if (count.get(x) == 1)
                return x;
        }
        return -1;
    }

    /******************************** Intuitive Bit manipulation solution ****************************
     * Time Complexity: O(n * 32)
     * Space Complexity: O(1)
     */
    public int singleNumber_(int[] nums) {
        int singleNumber = 0;

        for (int i = 0; i < 32; i++){
            int sum = 0;
            for (int num : nums){
                sum += (num >> i) & 1;
            }
            if (sum % 3 == 1)
                singleNumber |= (1 << i);
        }
        return singleNumber;
    }

    /******************************** Complex Bit manipulation solution ****************************
     * Intuition:
        * See video
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;

        for (int i = 0; i < nums.length; i++){
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }
}
