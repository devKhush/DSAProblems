package BinarySearch.MedianOfTwoSortedArrays;

// https://leetcode.com/problems/median-of-two-sorted-arrays/
// https://youtu.be/jDJuW7tSxio

import java.util.ArrayList;

public class MedianOfTwoSortedArrays {
    public double bruteForce(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] arr = new int[m+n];

        int i=0, j=0, k=0;
        while(i<m && j<n){
            if (nums1[i] <= nums2[j])
                arr[k++] = nums1[i++];
            else
                arr[k++] = nums2[j++];
        }
        while (i<m) arr[k++] = nums1[i++];
        while (j<n) arr[k++] = nums2[j++];

        if ((m+n)%2 != 0)
            return arr[(m+n)/2];
        else
            return (arr[(m+n)/2] + arr[((m+n)/2) - 1]) / 2.0;
    }


    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length){
            int[] temp = A;
            A = B;
            B = temp;
        }

        int totalElement = A.length + B.length;
        int half = (totalElement + 1) / 2;

        int low = 0, high = A.length;

        while(low <= high){
            int midA = (low + high)/2;
            int midB = half - midA;

            int ALeft = (midA - 1 >= 0) ? A[midA-1] : Integer.MIN_VALUE;
            int ARight = (midA < A.length) ? A[midA] : Integer.MAX_VALUE;
            int BLeft = (midB - 1 >= 0) ? B[midB-1] : Integer.MIN_VALUE;
            int BRight = (midB < B.length) ? B[midB] : Integer.MAX_VALUE;

            System.out.printf("midA: %d, midB: %d \n",midA, midB);
            System.out.println(ALeft + " "+ ARight);
            System.out.println(BLeft + " "+ BRight +"\n");

            if (ALeft <= BRight && BLeft <= ARight){
                if (totalElement%2 != 0)
                    return Integer.max(ALeft, BLeft);
                else
                    return (Integer.min(ARight, BRight) + Integer.max(ALeft, BLeft)) /2.0;
            }

            else if (ALeft > BRight)
                high = midA - 1;
            else if (BLeft > ARight)
                low = midA + 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] A = {1,2};
        int[] B = {3,4};

        ArrayList<Integer> arrayList = new ArrayList<>();

        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(A,B));
    }

}
