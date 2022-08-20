package Arrays.ThreeSumCloset;
import java.util.Arrays;

// PRE_REQUISITE: "THREE SUM"

class ThreeSumCloset {
    // Time Complexity : O(n*log(n)) + O(n^2)  ~  O(n^2)
    // Space Complexity : O(1)
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int absDifference = Integer.MAX_VALUE;
        int closetSum = 0;
        
        for (int i = 0; i < nums.length - 2; i++){
            int low = i + 1;
            int high = nums.length - 1;
            
            while (low < high){
                int sum = nums[i] + nums[low] + nums[high];
                
                if (sum == target)
                    return sum;
                else if (sum > target)
                    high--;
                else if (sum < target)
                    low++;
                
                if (absDifference > Math.abs(sum - target)){
                    closetSum = sum;
                    absDifference = Math.abs(sum - target);
                }
                
            }
        }
        return closetSum;
    }
}