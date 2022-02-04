package Sorting.MergeSort;

import java.util.Arrays;

public class MergeSort {

    public void merge(int[] arr, int[] temp, int l, int m, int h){
        int i=l, j=m+1, k = l;
        while (i<=m && j<=h){
            if (arr[i]>arr[j]){
                temp[k] = arr[j];
                j++; k++;
            }
            else{
                temp[k] = arr[i];
                k++; i++;
            }
        }
        while (i<=m){
            temp[k] = arr[i];
            k++; i++;
        }
        while (j<=h){
            temp[k] = arr[j];
            j++; k++;
        }
        for (int a=l; a<=h; a++) {
            arr[a] = temp[a];
        }
    }

    public void mergeSort(int[] arr, int[] temp, int l, int h){
        if (l<h){
            int mid = (l+h)/2;
            mergeSort(arr,temp,l,mid);
            mergeSort(arr,temp,mid+1,h);
            merge(arr, temp, l, mid, h);
        }
    }

    public void sort(int[] nums) {
        mergeSort(nums, new int[nums.length],0, nums.length-1);
    }

    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};
        new MergeSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
