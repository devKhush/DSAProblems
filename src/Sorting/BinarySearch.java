package Sorting;

public class BinarySearch {
    public int binarySearch(int[] arr, int low, int high, int target) {
        if (low > high)
            return -1;

        int mid = (low+high)/2;

        if (arr[mid]==target)
            return mid;
        else if (arr[mid]>target)
            return binarySearch(arr, low, mid-1, target);
        else
            return binarySearch(arr, mid+1, high, target);

    }
}
