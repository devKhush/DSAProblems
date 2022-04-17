class Solution {
    private int binarySearch(int[] arr, int low, int high, int target){
        if (low > high)
            return -1;
        int mid = (low + high)/2;
        
        if (arr[mid] == target)
            return mid;
        
        if (arr[mid] > target)
            return binarySearch(arr, low, mid-1, target);
        else
            return binarySearch(arr,mid+1, high, target);
    }
    
    public int search(int[] nums, int target) {
        // return binarySearch(nums, 0, nums.length-1, target);    
        
        int low = 0, high = nums.length-1;
        
        while (low <= high){
            int mid = (low + high)/2;
            
            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        
        return -1;
    }
}