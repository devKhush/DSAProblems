package Recursions_And_BackTracking.SubSets_or_SubSequence_OfArray_AnotherApproach;
import java.util.ArrayList;
import java.util.List;

// This is another approach for Printing all the Subsequence/Subsets/Combination of array elements

public class SubSequencesOfArray {

    private List<List<Integer>> subsets(int[] arr) {
        List<List<Integer>> answer = new ArrayList<>();

        getAllSubsequences(0, arr, answer, new ArrayList<>());
        return answer;
    }

    private void getAllSubsequences(int index, int[] arr, List<List<Integer>> allSubSequence, ArrayList<Integer> list){
        allSubSequence.add(new ArrayList<>(list));

        for (int i = index; i < arr.length; i++){

            list.add(arr[i]);
            getAllSubsequences(i+1, arr, allSubSequence, list);
            list.remove(list.size() - 1);

        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        System.out.println(new SubSequencesOfArray().subsets(arr));
    }
}
