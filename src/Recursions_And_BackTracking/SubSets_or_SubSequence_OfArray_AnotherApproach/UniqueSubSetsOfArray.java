package Recursions_And_BackTracking.SubSets_or_SubSequence_OfArray_AnotherApproach;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

// https://takeuforward.org/data-structure/subset-ii-print-all-the-unique-subsets/
// https://youtu.be/RIn3gOkbhQE

// Similar to Combination-Sum-II

class UniqueSubSetsOfArray {

    // ****************************************** Approach 1 ******************************************
    // Using new approach
    // T.C -> O(n*log(n)) (sorting) +  O(2^n) Due to time to calculate 2^n subsets in worst case
    // S.C -> O(2^n * k) to store every subset of average length k. Auxiliary space is O(n)  if n is the depth of the recursion tree.

    public List<List<Integer>> subsetsWithDup(int[] arr) {
        // Sorting makes easier to generate all unique subsequence with given sum, as repeating element
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


    // ****************************************** Approach 2 ******************************************
    // Same as previous one

    // T.C --> O(n*log(n)) (sorting) + O(2^n) (finding all subsets) +O(n * 2^n) (To add all subsets inside the set into list)
    // HashSet addition takes O(1) time

    // Space Complexity:  O(2^n * k) to store every subset of average length k.
    // Since we are initially using a set to store the answer another O(2^n *k) is also used.

    public List<List<Integer>> subsetsWithDup_(int[] arr) {
        // By sorting we ensure that every subset inside the all Unique combinations sets are in sorted fashion
        Arrays.sort(arr);

        HashSet<ArrayList<Integer>> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        List<List<Integer>> allUniqueSubsets = new ArrayList<>();

        // Finding all the unique subsets
        getAllSubsets(0, arr, list, set);

        for (ArrayList<Integer> li : set)
            allUniqueSubsets.add(li);

        // This also does the same thing, it converts HashSet of ArrayList into List of Lists
        // List<List<Integer>> allUniqueSubsets = new ArrayList<>(set);

        return allUniqueSubsets;
    }

    private void getAllSubsets(int i, int[] arr, ArrayList<Integer> list, HashSet<ArrayList<Integer>> set){
        if (i == arr.length){
            // adding the current subset into the set to ensure that only unique subsets are added
            set.add(new ArrayList<>(list));
            return;
        }

        // Not picking the current element
        // By not picking first, we will get sub-arrays like [[], [1], [2], [1,2]] for array {1,2}
        getAllSubsets(i+1, arr, list, set);

        // Picking the current element
        list.add(arr[i]);
        getAllSubsets(i+1, arr, list, set);
        list.remove(list.size() - 1);
    }
}