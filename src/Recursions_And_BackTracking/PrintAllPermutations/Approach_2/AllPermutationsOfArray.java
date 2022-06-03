package Recursions_And_BackTracking.PrintAllPermutations.Approach_2;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/f2ic2Rsc9pU
// https://takeuforward.org/data-structure/print-all-permutations-of-a-string-array/

class AllPermutationsOfArray {

    // ********************************** Approach 2 **********************************
    // Count of nth permutations of array of size 'n' = n!/(n-r)! = n! / 0! = n!

    // T.C --> O(permutation of array) + O(Adding permutations into arraylist)
    // T.C --> O(n * n!)
    // O(n * n!) due to generating & adding of all 'n!' permutations of size 'n' into ArrayList

    // S.C --> O(Recursion stack space) + O(Permutations array)
    // S.C. --> O(n) +  O(n!) = O(n)    if we ignore permutations array

    public List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> allPermutations = new ArrayList<>();
        
        allPermutations(0, arr, allPermutations);
        
        return allPermutations;
    }
    
    private void allPermutations(int index, int[] arr, List<List<Integer>> answer){
        if (index == arr.length){
            ArrayList<Integer> list = new ArrayList<>();
            for (int val : arr)
                list.add(val);
            
            answer.add(list);
        }

        for (int i = index; i < arr.length; i++){
            swap(index, i, arr);
            allPermutations(index + 1, arr, answer);
            swap(index, i, arr);
        }
    }
    
    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2,1,3};
        System.out.println(new AllPermutationsOfArray().permute(arr));
    }
}