package BinarySearch.KthSmallestElementInRowColumnWiseSortedMatrix;

// PRE-REQUISITE: "Median In Row Wise Sorted Matrix" Binary Search
// Entire Idea is same as that of that Question
// https://youtu.be/63fPPOdIr2c

/*
*************** Intuition ***************
We will replicate the same idea used in "Median In Row Wise Sorted Matrix" Binary Search Question

*************** Algorithm ***************:
1) First, we find the minimum and maximum elements in the matrix.
   This can be done in O(1) time as both the Rows & Columns of the matrix are sorted
2) Then we use binary search on our range of numbers from minimum to maximum, we find the 'mid' of
   the 'low' and 'high' and get a count of numbers less than or equal to our 'mid'.
   And accordingly change the "low" or "high".
3) For every number as our "mid", we get the count of numbers less than or equal to that by using
   countOfNumberLessThanOrEqualTo() in each row of the matrix.
   *  If this count is less than or equal to the required count "k", then the "kth Smallest number"
      must be greater than the selected number (as including the 'mid' there should be >= 'k'
      number on left side of it
      So, we make 'low' to "mid + 1"
   *  Else the 'mid' value can be our possible answer, i.e, "kth smallest number" must be less than or
      equal to the selected number. So, we SAVE the current value of 'mid' & make 'high' to "mid - 1"
*/

public class KthSmallestElementInSortedMatrix_BinarySearch {
    /*
     * Time Complexity: O(log2(max - min) * N * log(N))   =  O(N * log(N))
         The countOfNumberLessThanOrEqualTo() function will take "log(n)" time, and it is performed
         for each row.
         And since the numbers 'high' & 'low' will be max. & min. of array, so binary search of
         numbers from min to max will be performed in at most log2(max - min) operations.

     * Auxiliary Space: O(1)
    */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        // Finding the min. & max. element of the array to perform
        int low = matrix[0][0];
        int high = matrix[n - 1][n - 1];

        // Answer for "kth Smallest number"
        int kthSmallestElement = -1;

        // Binary Search on minimum & maximum element in the array
        while (low <= high){
            int mid = low + (high - low)/2;

            // Find count of elements smaller than or equal to current value of 'mid'
            int countLessThanOrEqual = 0;
            for (int i = 0; i < n; i++)
                countLessThanOrEqual += countLessThanOrEqualToMid(matrix[i], n, mid);

            // If 'count' is less than or equal to the required count k, the "kth Smallest number"  must
            // be greater than the 'mid' (as including the 'mid' there should be >= 'k' numbers
            // on left of it).
            if (countLessThanOrEqual < k)
                low = mid + 1;

            // Else the 'mid' value can be our possible answer, i.e, "kth Smallest number" must be less
            // than or equal to the selected number. So, we save the current value of 'mid'
            else if (countLessThanOrEqual >= k){
                kthSmallestElement = mid;
                high = mid - 1;
            }
        }
        // When low becomes greater than high, low will point to the "kth" Smallest element of the matrix.
        // return low;
        return kthSmallestElement;
    }


    // This function calculates the Count of numbers lesser than or equal to the no. 'value' in the array
    // Using Binary Search Algorithm
    // Problem is REDUCED to "Finding the index of first element greater than 'value' in the sorted array"
    // Using Binary Search Algorithm
    public int countLessThanOrEqualToMid(int[] arr, int n, int value){
        int countLessThanOrEqual = 0;
        int low = 0, high = n - 1;
        
        while (low <= high){
            int mid = low + (high - low)/2;
            
            if (arr[mid] <= value){
                countLessThanOrEqual = mid + 1;
                low = mid + 1;
            }
            else if (arr[mid] > value)
                high = mid - 1;
        }
        // 'low' will contain the index of the first element greater than 'value' in the sorted array
        // equal to number of element less than or equal to 'num'
        // return low;
        return countLessThanOrEqual;
    }
}

/*
And then after we do mid = (low+high)/2, and again we are uncertain whether mid even lies in the
matrix or not, and we keep doing it until we come to a favourable situation, and that uncertainty
still prevails, but how does this guarantees that whatever 'low' was last calculated actually lies
in the matrix???

1) That's why we keep moving the pointers 'low' & 'high', till we don't reach to a number where this
   pattern breaks...
2) Suppose, if current number 'mid' is not present in matrix, then the count of numbers
   lesser than or equal to 'mid' will be same as the "Maximum Smallest number" than 'mid' present
   in the matrix (think...).
   Then, two cases arises:
   2.1) If count of elements smaller or equal to 'mid' is smaller than k, then 'low' keeps on
        increasing to "mid + 1" until the low reaches a new element present in the matrix or greater
        than it.
   2.2) If count of elements smaller or equal to 'mid' is >= k, then 'high' keeps on decreasing
        to "mid - 1" until the high reaches a new element present in the matrix or smaller than it.
   This step (2) will continue to occur until low or high reaches to an element in the matrix.
   After that when low becomes greater than high', low will become equal to the element which is
   the "kth" Smallest element of the matrix.
   Try Dry Running over the Test-cases to understand it better:
   Matrix => [[1  , 5  , 9  ],
              [10 , 11 , 13 ],
              [12 , 13 , 15 ]], k = 8

Hence, low > high is the break point and 'low' will contain the "kth" Smallest element of the matrix.
 */
