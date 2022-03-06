package Sorting.SelectionSort;

import java.util.Arrays;

public class SelectionSort {
    public void selectionSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }

    public static void main(String[] args) {

        int[] arr = {9,8,7,6,5,3,345,67,6,3,5,3,5,6,7,2,1,0};

        new SelectionSort().selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
