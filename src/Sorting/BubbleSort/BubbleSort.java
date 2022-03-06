package Sorting.BubbleSort;

import java.util.Arrays;

public class BubbleSort {
    public void bubbleSort(int[] arr){
        int n = arr.length;
        int temp;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,3,345,67,6,3,5,3,5,6,7,2,1,0};

        new BubbleSort().bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
