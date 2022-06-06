package Arrays.SortAnArrayOf_0s_1s_2s;// { Driver Code Starts

// https://youtu.be/oaVa-9wmpns
// https://takeuforward.org/data-structure/sort-an-array-of-0s-1s-and-2s/
// Question of Striver SDE Sheet

class SortAnArray{


    //  ******************** Count Sort ==> Counting the number of  0s, 1s & 2s ********************
    // This is also known as Count Sort, a Sorting algorithm
    // T.C --> O(n)
    // S.C --> O(1)

    public static void sort012(int a[], int n){
        int count0 = 0;
        int count1 = 0;
        // int count2 = 0;

        for (int val : a){
            if (val==0) count0++;
            if (val==1) count1++;
            // if (val==2) count2++;
        }

        int i = 0;

        while (i < count0)  a[i++] = 0;

        while (i < count0  + count1)    a[i++] = 1;

        // We don't need a variable that stores count of 2, as array contains
        // only 0s, 1s & 2s (counts of 0s & 1s are already stored)
        // while (i < count0  + count1  + count2)
        //     a[i++] = 2;

        while (i < n)   a[i++] = 2;
    }


    // ********************************** Approach 2 **********************************
    // Dutch National flag algorithm

    public void sort012(int[] arr){
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high){
            switch (arr[mid]) {

                case 0 -> {
                    swap(low, mid, arr);
                    low++;
                    mid++;
                }

                case 1 -> mid++;

                case 2 -> {
                    swap(mid, high, arr);
                    high--;
                }
            }
        }

    }

    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}