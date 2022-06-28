class Solution {
    public int maxOperations(int[] arr, int k) {
        Arrays.sort(arr);
        
        int low = 0, high = arr.length - 1;
        int deleteOperations = 0;
        
        while (low < high){
            if (arr[low] + arr[high] == k){
                deleteOperations++;
                low++;
                high--;
            }
            else if (arr[low] + arr[high] < k)
                low++;
            else
                high--;
        }
        return deleteOperations;
    }
}