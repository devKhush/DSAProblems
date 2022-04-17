package BinarySearch.SearchInRotatedSortedArray;

// https://leetcode.com/problems/search-in-rotated-sorted-array/
// https://www.youtube.com/watch?v=r3pMQ8-Ad5s

public class SearchInRotatedSortedArray {
    public int search(int[] arr, int target) {
        int low = 0, high = arr.length-1;

        while(low<=high){
            int mid = (low+high)/2;

            if (arr[mid]==target)
                return mid;

            // left half is sorted
            if (arr[low]<=arr[mid]){
                if (target>=arr[low] && target<=arr[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            // right half is sorted
            else{
                if (target>=arr[mid] && target<=arr[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }

        return -1;
    }
}
