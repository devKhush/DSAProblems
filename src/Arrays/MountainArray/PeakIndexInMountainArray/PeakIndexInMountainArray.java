package Arrays.MountainArray.PeakIndexInMountainArray;

public class PeakIndexInMountainArray {
    /************************************* Binary Search ******************************************
     * Intuition:
        * Array is already mountain, we need to find the index where the peak is.
        * Use Binary Search can be used efficiently for this.

     * Time Complexity: O(log(n))
     * Space Complexity: O(1)
     */
    public int peakIndexInMountainArray(int[] arr) {
        int low = 1;
        int high = arr.length - 2;

        while (low <= high){
            int mid = low + ((high - low)>>1);
            if (arr[mid - 1] < arr[mid] && arr[mid] > arr[mid + 1])
                return mid;
            else if (arr[mid - 1] > arr[mid])
                high = mid - 1;
            else if (arr[mid] < arr[mid + 1])
                low = mid + 1;
        }
        return -1;
    }
}
