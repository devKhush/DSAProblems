package Arrays.MaximumProductSubarray;

// https://takeuforward.org/data-structure/maximum-product-subarray-in-an-array/
// https://youtu.be/hnswaLJvr6g
// https://leetcode.com/problems/maximum-product-subarray/description/

public class MaximumProductSubarray {
    /**************************************** Optimal Solution ****************************************
     * Intuition:
        * Partition array based on negative number and zero.
     * For intuition watch video
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int prefixProduct = 1, suffixProduct = 1;
        int maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++){
            prefixProduct *= nums[i];
            suffixProduct *= nums[n - 1 - i];
            maxProduct = Math.max(maxProduct, Math.max(prefixProduct, suffixProduct));

            if (prefixProduct == 0)
                prefixProduct = 1;
            if (suffixProduct == 0)
                suffixProduct = 1;
        }
        return maxProduct;
    }

    /**************************************** Brute Force ****************************************
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int maxProduct_brute(int[] nums) {
        int n = nums.length;
        int maxProduct = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++){
            int product = 1;
            for (int j  = i; j < n; j++){
                product *= nums[j];
                maxProduct = Math.max(maxProduct, product);
            }
        }
        return maxProduct;
    }
}
