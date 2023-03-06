package BinarySearch.KthMissingPositiveNumber;

public class KthMissingPositiveNumber {
    /*************************************** Binary Search *****************************************
     * Time Complexity: O(log(n))
     * Space Complexity: O(1)
     */
    public int findKthPositive(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high){
            int mid = low + (high - low)/2;

            if (k > arr[mid] - (mid + 1))
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low + k;
    }

    /******************************** Hashing Solution *******************************************
     * Time Complexity -> O(n)
     * Space Complexity -> O(n) or O(1)  depends on constraint
     */
    public int findKthPositive_Brute(int[] arr, int k) {
        boolean[] present = new boolean[2001];
        for (int ele: arr)
            present[ele] = true;

        for (int i = 1; i <= 2001; i++){
            if (!present[i])
                k--;
            if (k == 0)
                return i;
        }
        return -1;
    }
}
