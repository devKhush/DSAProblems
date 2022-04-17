package Sorting.ValidTriangleNumberCount;

// https://leetcode.com/problems/valid-triangle-number/
// https://www.youtube.com/watch?v=PqEiJDdt3S4

public class ValidTriangleNumber {

    public int partition(int[] arr, int low, int high){
        int pivot = arr[low];
        int i = low, j = high;

        while(i<=j){
            while (i<=high && arr[i]<=pivot)
                i++;
            while (arr[j]>pivot && j>low)
                j--;
            if (i<j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[low] = arr[j];
        arr[j] = pivot;
        return j;
    }

    public void quickSort(int[] arr, int low, int high){
        if (low<high){
            int partitionIndex = partition(arr, low, high);
            quickSort(arr, low, partitionIndex-1);
            quickSort(arr, partitionIndex+1, high);
        }
    }


    public int triangleNumber(int[] arr) {
        quickSort(arr, 0, arr.length-1);

        int triangleCount = 0;

        for (int i= arr.length-1; i>=0; i--){
            int left = 0, right = i-1;

            while (left<right){
                if (arr[left] + arr[right] > arr[i]){
                    triangleCount += (right-left);
                    right--;
                }
                else
                    left++;
            }
        }
        return triangleCount;
    }
}
