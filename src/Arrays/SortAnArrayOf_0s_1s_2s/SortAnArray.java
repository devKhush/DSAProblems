package Arrays.SortAnArrayOf_0s_1s_2s;// { Driver Code Starts

class SortAnArray{

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

        while (i < count0)
            a[i++] = 0;

        while (i < count0  + count1)
            a[i++] = 1;

        // We don't need a variable that stores count of 2, as array contains
        // only 0s, 1s & 2s (counts of 0s & 1s are already stored)
        // while (i < count0  + count1  + count2)
        //     a[i++] = 2;

        while (i < n)
            a[i++] = 2;

    }
}