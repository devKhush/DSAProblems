package Arrays.MoveZeros;

class MoveZeros {
    /*
    Simple Two pointer approach
    * TC -> O(n)
    * SC -> O(1)
    */
    public void moveZeroes(int[] arr) {
        int n = arr.length;

        int i = 0;
        // If the current element is not 0, then we need to
        // append it just in front of last non-zero element we found.
        for (int j = 0; j < n; j++){
            if (arr[j] != 0){
                arr[i] = arr[j];
                i++;
            }
        }
        
        // After we have finished processing all elements of array.
 	    // All the non-zero elements are already at beginning of array.
 	    // We just need to fill remaining array with 0's as we have overwritten zeros in the beginning of array
        while (i < n)
            arr[i++] = 0;
    }
}