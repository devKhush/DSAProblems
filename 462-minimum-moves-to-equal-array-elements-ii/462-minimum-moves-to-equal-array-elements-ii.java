class Solution {
    public int minMoves2_EfficientSolution(int[] arr) {
        Arrays.sort(arr);
        
        int mid = arr.length / 2;
        int low = 0, high = arr.length - 1;
        int moves = 0;
        
        while (low < mid)
            moves += arr[mid] - arr[low++];
        while(mid < high)
            moves += arr[high--] - arr[mid];
        return moves;
    }
    
     public int minMoves2_Solution1(int[] arr) {
        Arrays.sort(arr);
        
        int mid = arr.length / 2;
        int low = 0, high = arr.length - 1;
        int moves = 0;
       
        while (low < high){
            moves += arr[high] - arr[low];
            low++;
            high--;
        }
        return moves;
    }
    
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int mid = nums[nums.length / 2];
        int moves = 0;
        for (int n : nums) {
            moves += Math.abs(n - mid);
        }
        return moves;
    }
}