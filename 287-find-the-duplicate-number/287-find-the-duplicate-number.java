class Solution {
    
    public int findDuplicate(int[] arr){
        int slowPtr = arr[0];
        int fastPtr = arr[0];
        
        do {
            fastPtr = arr[arr[fastPtr]];
            slowPtr = arr[slowPtr];
        } while (fastPtr != slowPtr);
        
        fastPtr = arr[0];
        
        while (fastPtr != slowPtr){
            fastPtr = arr[fastPtr];
            slowPtr = arr[slowPtr];
        }
        
        return fastPtr;
    }
    
    
    public int findDuplicate_ArrayCount(int[] arr) {
        int n = arr.length;
        int[] count = new int[n];
        
        for (int i = 0; i < n; i++){
            count[arr[i]]++;
            
            if (count[arr[i]] > 1)
                return arr[i];
        }
        
        return -1;
    }
    
    
    public int findDuplicate_Sorting(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i-1])
                return nums[i];
        }

        return -1;
    }
    
    
    public int findDuplicate_HashMapCount(int[] nums) {
        HashMap<Integer, Integer> count = new HashMap<>();
        
        for (int num : nums){
            if (count.containsKey(num))
                return num;
            
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        
        return -1;
    }
}