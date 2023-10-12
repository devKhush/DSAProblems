package BinarySearch.FindInMountainArray;

// https://leetcode.com/problems/find-in-mountain-array/description/

public class FindInMountainArray {
    /*************************************** Binary Search *************************************
     * Time Complexity: O(n*log(n))
     * Space Complexity: O(1)
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();

        // Find the peak element of mountain array and separate it into two halves
        int low = 0, high = n - 2;
        int peak = 0;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if (mountainArr.get(mid) < mountainArr.get(mid + 1))
                low = mid + 1;
            else{
                peak = mid;
                high = mid - 1;
            }
        }
        // Search in left sorted increasing array
        low = 0; high = peak;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if (mountainArr.get(mid) == target)
                return mid;
            else if (mountainArr.get(mid) < target)
                low = mid + 1;
            else
                high = mid - 1;
        }
        // Search in right sorted decreasing array
        low = peak; high = n-1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if (mountainArr.get(mid) == target)
                return mid;
            else if (mountainArr.get(mid) < target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }


    /**************************************** API for Mountain Array *********************************/
    interface MountainArray {
        public int get(int index);
        public int length();
    }
}
