package Recursions.PartitionTokEqualSumSubsets;
import java.util.Arrays;

class PartitionTokEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k==0 || nums==null)
            return false;
        if (k==1)
            return true;
        
        int sum = 0;
        for (int value : nums)
            sum += value;
        
        if (sum % k != 0)
            return false;
        
        int eachPartitionSum = sum / k;
        
        Arrays.sort(nums);
        
        if (nums[nums.length-1] > eachPartitionSum)
            return false;
        
        return canPartition(nums, new boolean[nums.length], k, eachPartitionSum, 0, 0);
    }
    
    private boolean canPartition(int[] nums, boolean[] visited, int k, int eachPartitionSum, int index, int sumInProgress){
        System.out.println("\n********************************************* New  Call ******************************************");
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(visited));
        System.out.printf("index = %d, sumInProgress = %d, k = %d \n",index, sumInProgress, k);

        if (k==1)
            return true;
        
        if (sumInProgress > eachPartitionSum)
            return false;
        
        if (sumInProgress == eachPartitionSum)
            return canPartition(nums, visited, k-1, eachPartitionSum, 0, 0);
        
        for (int i = index; i < nums.length; i++){
            System.out.printf("\ni = %d, sumInProgress = %d \n",i, sumInProgress);
            System.out.println(Arrays.toString(visited));
            if (!visited[i]){
                visited[i] = true;

                System.out.printf("\ni = %d, sumInProgress = %d \n",i, sumInProgress);
                System.out.println(Arrays.toString(visited));

                if (canPartition(nums, visited, k, eachPartitionSum, i + 1, sumInProgress + nums[i]))
                    return true;

                System.out.println("....................... Returned .......................");
                visited[i] = false;
            }

            if (!visited[i]){
                while (i < nums.length-1  &&  nums[i] == nums[i+1])
                    i++;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {4,3,2,3,5,2,1};
        int k = 4;
        System.out.println(new PartitionTokEqualSumSubsets().canPartitionKSubsets(arr, k));
    }
}








