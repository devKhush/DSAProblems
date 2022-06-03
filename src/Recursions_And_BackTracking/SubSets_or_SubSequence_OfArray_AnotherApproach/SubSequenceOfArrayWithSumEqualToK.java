package Recursions_And_BackTracking.SubSets_or_SubSequence_OfArray_AnotherApproach;
import java.util.ArrayList;
import java.util.List;

// To see all Unique Sub-Sequences of array with sum equal to 'k', see "Combination Sum II"

public class SubSequenceOfArrayWithSumEqualToK {
    private List<List<Integer>> subsequenceWithSumEqualToK(int[] arr, int k){
        List<List<Integer>> allSubSequencesWithSumK = new ArrayList<>();

        getSubSequencesWithSumK(0, 0, k, arr, allSubSequencesWithSumK, new ArrayList<>());
        return allSubSequencesWithSumK;
    }

    private void getSubSequencesWithSumK(int index, int sum, int k, int[] arr, List<List<Integer>> answer, ArrayList<Integer> list){
        if (sum == k)
            answer.add(new ArrayList<>(list));

        for (int i = index; i < arr.length; i++) {

            list.add(arr[i]);
            getSubSequencesWithSumK(i+1, sum + arr[i], k, arr, answer, list);
            list.remove(list.size()-1);
        }
    }


    public static void main(String[] args) {
        int[] arr = {3, 1, 2};
        List<List<Integer>> answer = new SubSequenceOfArrayWithSumEqualToK().subsequenceWithSumEqualToK(arr, 3);

        System.out.println(answer);
        System.out.println(answer.size());
    }
}
