class Solution {
    public int singleNonDuplicate(int[] nums) {
        int xor = 0;
        for (int value : nums)
            xor ^= value;
        return xor;
    }
}