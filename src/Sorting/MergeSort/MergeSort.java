package Sorting.MergeSort;

import java.util.Arrays;

public class MergeSort {

    public void merge(int[] arr, int[] temp, int low, int mid, int high){
        int i = low, j = mid+1;
        int k = low;

        while(i<=mid && j<=high){
            if (arr[i]<=arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        while (i<=mid)
            temp[k++] = arr[i++];
        while (j<=high)
            temp[k++] = arr[j++];
        for (int a=low; a<=high; a++)
            arr[a] = temp[a];
    }

    public void mergeSort(int[] arr, int[] temp, int low, int high){
        if (low<high){
            int mid = (low+high)/2;
            mergeSort(arr, temp, low, mid);
            mergeSort(arr, temp, mid+1, high);
            merge(arr, temp, low, mid, high);
        }
    }

    public int[] sortArray(int[] nums) {
        mergeSort(nums, new int[nums.length], 0, nums.length-1);
        return nums;
    }

    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};
        new MergeSort().sortArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}
