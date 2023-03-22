package Arrays.NumberofZeroFilledSubarrays;

public class NumberOfZeroFilledSubArrays {
    /*********************************** Solution 1 *******************************************
     * Intuition: Count NUmber of Arrays filled with 0's
     * TC -> O(n)
     * SC -> O(1)
     */
    public long zeroFilledSubarray(int[] nums) {
        long subArrays = 0;
        int i = 0, n = nums.length;
        while (i < n){
            if (nums[i] == 0){
                int k = i;
                long cnt = 0;
                while (k < n && nums[k] == 0){
                    cnt++;
                    k++;
                }
                subArrays += cnt*(cnt + 1)/2;
                i = k;
            }
            i++;
        }
        return subArrays;
    }


    /*********************************** Solution 2 *******************************************
     * Intuition: Count Number of Arrays filled with 0's
     * TC -> O(n)
     * SC -> O(1)
     */
    public long zeroFilledSubarray_(int[] nums) {
        long subArrays = 0;
        long currLen = 0;

        for (int val : nums){
            if (val == 0)
                subArrays += ++currLen;
            else
                currLen = 0;
        }
        return subArrays;
    }
}
