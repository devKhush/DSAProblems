package Arrays.ConvertArrayIntoZigZagFashion;

// https://youtu.be/bgx1eAgBgaQ
// https://www.geeksforgeeks.org/convert-array-into-zig-zag-fashion/
// https://practice.geeksforgeeks.org/problems/convert-array-into-zig-zag-fashion1638/1/

class ConvertArrayIntoZigZagFashion {

    /*
     ******************************** Simple Solution *****************************************
     TC -> O(n)
     SC -> O(1)
     Idea it to observe a pattern:
     -> For even index we want element should be smaller than both its left and right element
     -> For odd index we want element should be greater than both its left and right element

     * We only check for the relation between current element & its next element AND
     not for the relation between current element & previous element, because the relation
     between current element & its previous element was already taken care by it previous element
     i.e, during previous iteration of loop
     */

    public void zigZag(int[] arr, int n) {

        for (int i = 0; i < n-1; i++){
            // For even index, we want element should be smaller than both its left and right element
            // If at even index, current element is greater than its next index, simply swap it
            // else if at even index, current element is smaller than its next element, don't do anything
            // as it is already in correct place
            if (i % 2 == 0){
                if (arr[i] > arr[i+1])
                    swap(i, i+1, arr);
            }
            // For odd index, we want element should be greater than both its left and right element
            // if at odd index, current element is smaller than its next index, simply swap it
            // else if at odd index, current element is greater than its next element, don't do anything
            // as it is already in correct place
            else{
                if (arr[i] < arr[i+1])
                    swap(i, i+1, arr);
            }
        }
    }
    
    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}