package BitManipulation.SingleNumber;
import java.util.HashMap;

// https://leetcode.com/problems/single-number/

public class SingleNumber {
    /*********************************** Brute HashMap Solution ********************************
     * Time Complexity: O(n)
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

    /*********************************** Efficient XOR Solution ************************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int singleNumber(int[] nums) {
        int xor = 0;
        for (int val : nums)
            xor ^= val;
        return xor;
    }
}
