package BinarySearch;

// https://leetcode.com/problems/single-element-in-a-sorted-array/submissions/
// https://www.youtube.com/watch?v=nMGL2vlyJk0

public class SingleElementInSortedArray {

    public static int bruteForceSolution(int[] arr){        // O(N)
        int XOR = 0;                // By taking XOR of all values in ARRAY
        for (int j : arr)           // After that that number will be XOR itself
            XOR = XOR ^ j;
        return XOR;
    }

    public int singleNonDuplicate(int[] arr) {
        int low = 0, high = arr.length-1;
        if (high == 0)
            return arr[high];
        if (arr[low] != arr[low+1])
            return arr[low];
        if (arr[high] != arr[high-1])
            return arr[high];

        while(low <= high){
            int mid = (low+high)/2;

            if (arr[mid-1]!=arr[mid] && arr[mid+1]!=arr[mid])
                return arr[mid];

            if (mid%2==0){
                if (arr[mid] == arr[mid+1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            else{
                if (arr[mid]==arr[mid-1])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr  = {1,1,2,2,3,3,4,4,5,6,6};

        System.out.println(bruteForceSolution(arr));
        System.out.println(new SingleElementInSortedArray().singleNonDuplicate(arr));
    }
}
