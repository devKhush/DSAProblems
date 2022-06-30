// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;

class GFG{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t > 0){
			int n = sc.nextInt();
			int[][] a = new int[n][n];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					a[i][j] = sc.nextInt();
			Solution T = new Solution();
			ArrayList<Integer> arr= T.mergeKArrays(a, n);
			for(int i = 0; i < n*n ; i++)
			    System.out.print(arr.get(i)+" ");
		    System.out.println();
		    
		    t--;
		}
	}
}

// } Driver Code Ends


//User function Template for Java


class Solution
{
    //Function to merge k sorted arrays.
    public static ArrayList<Integer> mergeKArrays(int[][] kArrays,int K) 
    {
        ArrayList<Integer> mergedKSortedList = new ArrayList<>();
        ArrayList<Integer> arr1 = new ArrayList<>();

        for (int i = 0; i < kArrays[0].length; i++)
            arr1.add(kArrays[0][i]);

        for (int ind = 1; ind < kArrays.length; ind++){
            int[] arr2 = kArrays[ind];

            int i = 0;
            int j = 0;
            while (i < arr1.size() || j < arr2.length){
                int valA = i < arr1.size() ? arr1.get(i) : Integer.MAX_VALUE;
                int valB = j < arr2.length ? arr2[j] : Integer.MAX_VALUE;

                if (valA <= valB) {
                    mergedKSortedList.add(valA);
                    i++;
                }
                else {
                    mergedKSortedList.add(valB);
                    j++;
                }
            }
           arr1.clear();
            ArrayList<Integer> temp = arr1;
            arr1 = mergedKSortedList;
            mergedKSortedList = temp;
        }
        mergedKSortedList = arr1;
        return mergedKSortedList;
    }
}