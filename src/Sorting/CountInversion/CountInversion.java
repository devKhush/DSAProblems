package Sorting.CountInversion;


// It is entirely based on MERGE SORT Algorithm

// https://practice.geeksforgeeks.org/problems/inversion-of-array-1587115620/1/
// https://www.geeksforgeeks.org/counting-inversions/
// https://youtu.be/kQ1mJlwW-c0
// https://takeuforward.org/data-structure/count-inversions-in-an-array/
// Question of Striver SDE sheet

import java.util.Arrays;
import java.util.Scanner;

public class CountInversion {

    // ***************************** Using Merge Sort *****************************************
    // T.C --> O(n * log(n))
    // S.C --> O(n)

    public static long mergeAndSplitInversion(long[] arr, long[] temp, int low, int mid, int high){
        int i = low, j = mid+1, k = low;
        long inversionCount = 0;

        while(i<=mid && j<=high){

            // if current element in left half is <= current element in right half, then there is no inversions
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
            }

            // if current element in left half is > current element in right half, then there are inversions
            // these inversions are equal in "mid - i + 1" in numbers (Do dry run!)
            else{
                inversionCount += mid - i + 1;
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= mid){
            temp[k++] = arr[i++];
        }
        while (j <= high){
            temp[k++] = arr[j++];
        }

        for (int index = low; index <=high; index++)
            arr[index] = temp[index];

        return inversionCount;
    }

    public static long sortAndCountInversion(long[] arr, long[] temp, int low, int high){

        if (low<high){
            int mid = (low+high)/2;

            long leftInversionCount = sortAndCountInversion(arr, temp, low, mid);
            long rightInversionCount = sortAndCountInversion(arr, temp, mid+1, high);
            long splitInversionCount = mergeAndSplitInversion(arr, temp, low, mid, high);

            return leftInversionCount + rightInversionCount + splitInversionCount;
        }
        return 0;
    }

    public static long inversionCount(long[] arr, long N) {
        return sortAndCountInversion(arr, new long[(int) N], 0, (int) (N-1));
    }



    // ***************************** Brute Force *****************************************
    // T.C --> O(n * n)
    // S.C --> O(1)

    private long getInversion_BruteForce(int[] arr){
        long inversionsCount = 0;

        for (int i = 0; i < arr.length - 1; i++){
            for (int j = i + 1; j < arr.length; j++){
                if (arr[i] > arr[j])
                    inversionsCount++;
            }
        }
        return inversionsCount;
    }

    public static void main(String[] args) {
//        long arr[] = {468, 335, 1, 170, 225, 479, 359, 463, 465, 206, 146, 282, 328, 462, 492, 496, 443, 328, 437, 392, 105, 403, 154, 293, 383, 422, 217, 219, 396, 448, 227, 272, 39, 370, 413, 168, 300, 36, 395, 204, 312, 323,};
//        long n = 42;

        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long arr[] = new long[(int) n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        System.out.println(inversionCount(arr,n));
        System.out.println(Arrays.toString(arr));
    }
}
