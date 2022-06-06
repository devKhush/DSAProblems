class Solution {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i-1])
                return nums[i];
        }

        return -1;
    }
    
    
    public int findDuplicate_(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        
        for (int num : nums){
            if (count.containsKey(num))
                return num;
            
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        return -1;
    }
}