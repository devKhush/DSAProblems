package BinarySearch.MaximumValueAtAGivenIndexInBoundedArray;

// https://leetcode.com/problems/maximum-value-at-a-given-index-in-a-bounded-array/description/
// https://youtu.be/dd2gFD6tlrU (Intuition)

public class MaximumValueAtAGivenIndexInBoundedArray {
    /************************************* Brute Binary Search Solution *********************************
     * Intuition:
     * Binary Search:
        * To find the to maximize the value at the arr[index]
        * If a particular value of arr[index] is maximized enough that is satisfies all the given condition.
        * Then, any value less than arr[index] at the 'index' would satisfy that condition too.
        * Similarly, for opposite condition when arr[index] cound't meet the conditions, its greater value
            won't meet condition too.
        * So, use Binary Search to find the possible next greater arr[index]
     * Greedy:
        * Since, we need to have arraySum <= maxSum, it makes sense (greedy nature) to have a mountain
            like structure at the arr[index], with the left and right values decreasing by 1.
        * Assign remaining elements to 1.
        * By using this observation, we will minimize the sum of array to increase the arr[index]
        * To have array_sum <= maxSum, we will try to put the other elements in array as minimum as possible.
        * For eg:   1 1 2 3 4 5 4 3 2 1 1 1 1

     * Time Complexity: O(n * log(maxSum))
     * Space Complexity: O(1)
     */
    public int maxValue_Brute(int n, int index, int maxSum) {
        int low = 1, high = maxSum;
        int maxVal = -1;

        while (low <= high){
            int mid = low + ((high - low)>>1);
            long arraySum = mid;

            // Accumulate the sum of elements on left side of the array
            int j = index - 1, leftVal = mid - 1;
            while (j >= 0){
                arraySum += Math.max(1, leftVal);
                leftVal--;
                j--;
            }
            // Accumulate the sum of elements on right side of the array
            int i = index + 1, rightVal = mid - 1;
            while (i < n){
                arraySum += Math.max(1, rightVal);
                rightVal--;
                i++;
            }

            // Binary Search logic
            if (arraySum <= maxSum){
                low = mid + 1;
                maxVal = mid;
            }
            else
                high = mid - 1;
        }
        return maxVal; // or return high;
    }


    /******************************** Efficient Binary Search + AP Solution *****************************
     * Improvement:
        * To accumulating the sum of elements on the left and right side, we use AP formula
            instead of looping.

     * Time Complexity: O(log(maxSum))
     * Space Complexity: O(1)
     */
    public int maxValue(int n, int index, int maxSum) {
        int low = 1, high = maxSum;
        int maxVal = -1;

        while (low <= high){
            int mid = low + ((high - low)>>1);
            long arraySum = mid;

            // Accumulate the sum of elements on left side of the array
            if (mid - 1 >= index){
                long start = mid - index;
                long end = mid - 1;
                long numElements = index;
                arraySum += numElements * (start + end) / 2;
            }
            else{
                long start = 1;
                long end = mid - 1;
                long numElements = mid - 1;
                arraySum += numElements * (start + end) / 2;
                arraySum += index - (mid - 1);
            }
            // Accumulate the sum of elements on right side of the array
            if (mid - 1 >= n - 1 - index){
                long start = mid - (n - 1 - index);
                long end = mid - 1;
                long numElements = n - 1 - index;
                arraySum += numElements * (start + end) / 2;
            }
            else{
                long start = 1;
                long end = mid - 1;
                long numElements = mid - 1;
                arraySum += numElements * (start + end) / 2;
                arraySum += (n - 1 - index) - (mid - 1);
            }

            // Binary Search logic
            if (arraySum <= maxSum){
                low = mid + 1;
                maxVal = mid;
            }
            else
                high = mid - 1;
        }
        return maxVal;
    }

}
