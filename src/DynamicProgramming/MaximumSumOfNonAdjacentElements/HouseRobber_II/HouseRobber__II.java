package DynamicProgramming.MaximumSumOfNonAdjacentElements.HouseRobber_II;

import java.util.ArrayList;

class HouseRobber__II {
    public int rob(ArrayList<Integer> arr) {
        
        int maxSumPrevTwoIndex = 0;  
        int maxSumPrevOneIndex = arr.get(0);
        
        for (int i = 1; i < arr.size(); i++){
            int maxSumByPicking = arr.get(i) + maxSumPrevTwoIndex;
            int maxSumByNotPicking = 0 + maxSumPrevOneIndex;
            
            int maxSumAt_IthIndex = Math.max(maxSumByNotPicking, maxSumByPicking);
            
            maxSumPrevTwoIndex = maxSumPrevOneIndex;
            maxSumPrevOneIndex = maxSumAt_IthIndex;
        }
        
        return maxSumPrevOneIndex;
    }
    
    
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return nums[0];

        ArrayList<Integer> arr1 = new ArrayList<>();       
        ArrayList<Integer> arr2 = new ArrayList<>();
                
        for (int i = 0; i < n; i++){
            if (i != 0)
                arr2.add(nums[i]);
            
            if (i != n-1)
                arr1.add(nums[i]);
        }
        
        int maxSum_From_0_to_Nminus2 = rob(arr1);    
        int maxSum_From_1_to_Nminus1 = rob(arr2);
        
        return Math.max(maxSum_From_0_to_Nminus2, maxSum_From_1_to_Nminus1);

    }
}