package Recursions.PartitionTokEqualSumSubsets;
import java.util.Arrays;

// https://www.youtube.com/watch?v=qpgqhp_9d1s&t=45s
// https://youtu.be/mBk4I0X46oI
// https://www.youtube.com/watch?v=8t8TeyRJDvk

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

        // sorting here makes base cases & for loop cases reach faster
        Arrays.sort(nums);

        // if last element is greater than required sum of partition set, then the partition
        // sets can't be formed
        if (nums[nums.length-1] > eachPartitionSum)
            return false;
        
        return canPartition(nums, new boolean[nums.length], k, eachPartitionSum, 0, 0);
    }
    
    private boolean canPartition(int[] nums, boolean[] visited, int k, int eachPartitionSum, int index, int sumInProgress){
        if (k == 1)
            return true;

        // if sub set sum is greater than required partition sum, then set can't be formed
        if (sumInProgress > eachPartitionSum)
            return false;

        // if sub set sum is equal to required partition sum, then set can be formed
        // so check for remaining k-1 subsets
        if (sumInProgress == eachPartitionSum)
            return canPartition(nums, visited, k-1, eachPartitionSum, 0, 0);
        
        for (int i = index; i < nums.length; i++){
            if (!visited[i]){
                //assume that with current element, a required partition set with required
                // sum can be formed and mark it visited
                visited[i] = true;

                // visit each remaining element in the array one by one & checks whether these remaining element
                // in the array can obtain required partition set sum
                if (canPartition(nums, visited, k, eachPartitionSum, i + 1, sumInProgress + nums[i]))
                    return true;

                // if required partition set sum can't be formed with current element,
                // then don't use it and mark it unused
                visited[i] = false;
            }

            // if required partition set sum can't be formed with current element,
            // then don't use it. Also, we can skip the next element in array if they are
            // same as previous one, as they can form partition set too
            if (!visited[i]){
                while (i < nums.length-1  &&  nums[i] == nums[i+1])
                    i++;
            }
        }

        // return false as partition set can't be formed with given combination of elements in array or
        // with given elements of array
        return false;
    }
}