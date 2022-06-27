// { Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
class GFG {
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int t=sc.nextInt();
		
		while(t-->0)
		{
		    int n=sc.nextInt();
		    int a[]=new int[n];
		    
		    for(int i=0;i<n;i++)
		    {
		        a[i]=sc.nextInt();
		    }
		    int m=sc.nextInt();
		    Solution ob = new Solution();
		    System.out.println(ob.findPages(a,n,m));
		}
	}
}// } Driver Code Ends


//User function Template for Java

class Solution {
    //Function to find minimum number of pages.
    public static int findPages(int[] bookArray, int n, int students){
           int maxPageInBooksOfArray = 0;
        int sumOfAllPagesInBookOfArray = 0;

        for (int pages : bookArray){
            sumOfAllPagesInBookOfArray += pages;
            maxPageInBooksOfArray = Math.max(pages, maxPageInBooksOfArray);
        }

        int low = maxPageInBooksOfArray;
        int high = sumOfAllPagesInBookOfArray;
        int minimumPagesAllocated = -1;

        while (low <= high){
            int midPages = (low + high) >> 1;

            if (canAllocateCurrentPagesAsMinimumPages(bookArray, students, midPages)){
                minimumPagesAllocated = midPages;
                high = midPages - 1;
            }
            else
                low = midPages + 1;
        }
        return minimumPagesAllocated;
    }
     private static boolean canAllocateCurrentPagesAsMinimumPages(int[] bookArray, int students, int pageToBeAllocate){
        int studentsAllocatedBooks = 1;
        int pagesAllocatedToCurrentStudent = 0;

        for (int currentBookPages : bookArray) {
            if (pagesAllocatedToCurrentStudent + currentBookPages  <=  pageToBeAllocate)
                pagesAllocatedToCurrentStudent += currentBookPages;
            else if (pagesAllocatedToCurrentStudent + currentBookPages  >  pageToBeAllocate) {
                studentsAllocatedBooks++;
                pagesAllocatedToCurrentStudent = currentBookPages;
            }
        }
        return studentsAllocatedBooks <= students;
    }
}