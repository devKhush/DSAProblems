package BinarySearch.CountNegativeNumbersInSortedMatrix;

// https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/description/

public class CountNegativeNumbersInSortedMatrix {
    /*************************************** Binary Search ******************************************
     * Time Complexity: O(m * log(n))
     * Space Complexity: O(1)
     */
    public int countNegatives_BinarySearch(int[][] grid) {
        int count = 0;

        for (int[] arr : grid){
            int low = 0, high = arr.length - 1;
            while (low <= high){
                int mid = low  + ((high - low)>>1);
                if (arr[mid] >= 0)
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            count += arr.length - low;
        }
        return count;
    }


    /********************************** Optimal Two pointer solution ************************************
     * Intuition: Since, both row and columns are sorted.
     * We can traverse each row in grid, till we find first negative element and accordingly update count.

     * Time Complexity: O(m + n)
     * Space Complexity: O(1)
     */
    public int countNegatives(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int i = 0, j = n - 1;
        int count = 0;

        while (i < m && j >= 0){
            while (j >= 0 && grid[i][j] < 0)
                j--;
            count += n - 1 - j;
            i++;
        }
        if (i < m){
            count += n * (m - i);
        }
        return count;
    }

}
