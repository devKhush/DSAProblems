package Sorting.QuickSort;

public class QuickSort {
    // Quick Sort
    public int partition(int[] arr, int low, int high){
        int pivot = arr[low];
        int i = low + 1, j = high;

        int temp;
        while(i <= j){
            while (i<=high && arr[i]<=pivot)
                i++;
            while (arr[j]>pivot && j>low)
                j--;
            if (i<j){
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        temp = arr[j];
        arr[j] = arr[low];
        arr[low] = temp;
        return j;
    }

    public void quickSort(int[] arr, int low, int high){
        if (low<high){
            int partitionIndex = partition(arr,low,high);
            quickSort(arr, low, partitionIndex-1);
            quickSort(arr, partitionIndex+1, high);
        }
    }

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length-1);
        return nums;
    }
}
