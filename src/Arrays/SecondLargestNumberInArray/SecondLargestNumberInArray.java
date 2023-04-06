package Arrays.SecondLargestNumberInArray;

public class SecondLargestNumberInArray {
    /*********************************** Solution 1 **********************************************
     * TC -> O(2*n) ~ O(n)
     * SC -> O(1)
     */
    int print2largest(int[] arr, int n) {
        int firstMax = 0;
        for (int val : arr)
            firstMax = Math.max(firstMax, val);

        int secondMax = 0;
        for (int val : arr){
            if (val != firstMax)
                secondMax = Math.max(secondMax, val);
        }
        return secondMax != 0 ? secondMax : -1;
    }

    /*********************************** Solution 2 **********************************************
     * TC -> O(n)
     * SC -> O(1)
     */
    int print2ndLargest(int[] arr, int n) {
        int firstMax = 0, secondMax = 0;

        for (int val : arr){
            if (val > firstMax){
                secondMax = firstMax;
                firstMax = val;
            }
            else if (val < firstMax  &&  val > secondMax)
                secondMax = val;
        }
        return secondMax != 0 ? secondMax : -1;
    }
}
