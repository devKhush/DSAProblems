package BinarySearch.SearchInRotatedSortedArray_II;

// PRE_REQUISITE: "Search In Rotated Sorted Array"
// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/solutions/1890199/c-algorithm-binary-search/

public class SearchInRotatedSortedArray_II {
    /*************************************** Binary Search ******************************************
     * This problem is same as that of "Search In Rotated Sorted Array", but with slight variation that
         element might not be unique.


     * Time Complexity: O(log(n))
     * Space Complexity: O(1)
     */
    public boolean search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        // Same Binary Search as before
        while (low <= high){
            int mid = low + (high - low)/2;
            if (arr[mid] == target)
                return true;

            // logic to skip duplicates, see other link
            if (arr[low] == arr[mid] && arr[mid] == arr[high]){
                low++;
                high--;
            }

            // Same code as before
            else if (arr[low] <= arr[mid]){
                if (arr[low] <= target && target <= arr[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            else{
                if (arr[mid] <= target && target <= arr[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return false;
    }
}
