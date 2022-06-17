class NumArray {
    private int[] prefix;
    public NumArray(int[] nums) {
        this.prefix = new int[nums.length];
        int prefixSum = 0;
        
        for (int i = 0; i < nums.length; i++){
            prefixSum += nums[i];
            prefix[i] = prefixSum;
        }
    }
    
    public int sumRange(int left, int right) {
        return left - 1 >= 0 ? prefix[right] - prefix[left - 1] : prefix[right];
    }
}



/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */