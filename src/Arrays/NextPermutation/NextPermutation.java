package Arrays.NextPermutation;

// All the permutations of the (sorted) array in lexicographically greater (dictionary) order can
// be made by boolean visited array approach, that approach makes all the permutation of sorted array
// in lexicographically greater order
// Mountain Shape
// For intuition watch VIDEO
// https://youtu.be/LuLCLgMElus
// https://takeuforward.org/data-structure/next_permutation-find-next-lexicographically-greater-permutation/
// Question of Striver SDE sheet
// https://youtu.be/JDOXKqF60RQ (NEW)


public class NextPermutation {
    /*********************************** Solution 1 **************************************
     * TC -> O(n)
     * SC -> O(1)
     */
    public void nextPermutation(int[] arr) {
        int n = arr.length;

        // Element at index 'i' in array will be breakdown index
        int i = n - 2;
        while (i >= 0 && arr[i] >= arr[i + 1])
            i--;

        // Special case of last permutation
        if (i == -1){
            reverse(0, n - 1, arr);
            return;
        }

        // Swapping the element at breakdown index 'i' with the element just greater than it
        for (int j = n - 1; j >= 0; j--){
            if (arr[i] < arr[j]){
                swap(i, j, arr);
                break;
            }
        }
        // Reversing the remaining array
        reverse(i + 1, n - 1, arr);
    }


    // ******************************** Another Code *************************************
    /*********************************** Solution 2 **************************************
     * TC -> O(n)
     * SC -> O(1)
     */
    public void nextPermutation_(int[] arr) {
        // Element at index 'i' in array will be breakdown index
        // 'i' is the index which is the start of the increasing sequence in backward direction.
        int i = arr.length - 2;
        while(i >= 0 && arr[i] >= arr[i + 1])
            i--;

        if(i >= 0) {
            // Find an element that has a value greater than the element at breakpoint index 'i'
            int j = arr.length - 1;
            while(arr[i] >= arr[j])
                j--;

            swap(i, j, arr);
        }
        reverse(i + 1, arr.length - 1, arr);
    }


    // Simple swapping code
    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Simple array reversal code
    private void reverse(int low, int high, int[] arr) {
        while (low < high)
            swap(low++, high--, arr);
    }
}
