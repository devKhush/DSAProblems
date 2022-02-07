package Sorting.MergeSort;

// https://leetcode.com/problems/merge-sorted-array/

import java.util.Arrays;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = m + n -1;

        while (i>=0 && j>=0 && k>=0){
            if (nums2[j] > nums1[i])
                nums1[k-- ] = nums2[j--];
            else
                nums1[k--] = nums1[i--];
        }
        while (i>=0 && k>=0)
            nums1[k--] = nums1[i--];
        while (j>=0 && k>=0)
            nums1[k--] = nums2[j--];
    }

    public static void main(String[] args) {
//        int[] arr1 = new int[]{1, 2, 3, 6, 0, 0, 0};
//        int[] arr2 = new int[]{2, 5, 7};

        int[] arr1 = new int[]{0};
        int[] arr2 = new int[]{1};
        new MergeSortedArray().merge(arr1, 0, arr2, 1);

        System.out.println(Arrays.toString(arr1));

    }
}
