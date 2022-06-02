package Recursions_And_BackTracking.SubSets_or_SubSequence_OfArray_AnotherApproach;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// Similar to Combination-Sum-II

class UniqueSubSetsOfArray {
    public List<List<Integer>> subsetsWithDup(int[] arr) {
        // Sorting makes easier to generate all subsequence with given sum, as repeating element
        // can be easily ignored
        Arrays.sort(arr);
        
        List<List<Integer>> allUniqueSubsets = new ArrayList<>();
        
        getAllUniqueSubsets(0, arr, allUniqueSubsets, new ArrayList<>());
        return allUniqueSubsets;
    }
    
    private void getAllUniqueSubsets(int index, int[] arr, List<List<Integer>> answer, ArrayList<Integer> list){
        answer.add(new ArrayList<>(list));
        
        for (int i = index; i < arr.length; i++){

            // As the combinations should be unique (no repeating combination like [1,2,2] & [1,2,2] for two repeating 2's)
            // If this element has been previously considered in the unique combinations, we can skip it
            if (i > index  && arr[i] == arr[i-1])
                continue;
            
            list.add(arr[i]);
            getAllUniqueSubsets(i+1, arr, answer, list);
            list.remove(list.size() - 1);
        }
    }
}