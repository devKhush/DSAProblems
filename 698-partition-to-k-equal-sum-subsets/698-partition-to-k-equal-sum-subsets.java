class Solution {
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
        if (k==1)
            return true;
        
        if (sumInProgress > eachPartitionSum)
            return false;
        
        if (sumInProgress == eachPartitionSum)
            return canPartition(nums, visited, k-1, eachPartitionSum, 0, 0);
        
        for (int i = index; i < nums.length; i++){
            if (!visited[i] &&  sumInProgress + nums[i] <= eachPartitionSum){
            
                visited[i] = true;
                
                if (canPartition(nums, visited, k, eachPartitionSum, i + 1, sumInProgress + nums[i]))
                    return true;
                
                visited[i] = false;
            }
            
            if (!visited[i]){
                while (i < nums.length-1 && nums[i]==nums[i+1])
                    i++;
            }
        }
        return false;
    }
}








