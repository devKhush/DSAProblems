class Solution {
    public int findDuplicate(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        
        for (int num : nums){
            if (count.containsKey(num))
                return num;
            
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        return -1;
    }
}