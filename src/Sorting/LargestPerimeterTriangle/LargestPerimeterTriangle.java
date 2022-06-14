package Sorting.LargestPerimeterTriangle;
import java.util.Arrays;

class LargestPerimeterTriangle {

    // Simple logic of sorting
    // TC -> O(n * log(n)) + O(n)
    // SC -> O(1) ignoring space due to sorting

    public int largestPerimeter(int[] arr) {
        Arrays.sort(arr);
        
        for (int i = arr.length - 3; i >= 0; i--){
            if (arr[i] + arr[i + 1] > arr[i + 2]){
                return arr[i] + arr[i + 1] + arr[i + 2];
            }
        }
        return 0;
    }
}