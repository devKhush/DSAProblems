package Recursions_And_BackTracking.SubSets_or_SubSequence_OfArray_AnotherApproach;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

// https://youtu.be/RIn3gOkbhQE
// https://takeuforward.org/data-structure/subset-ii-print-all-the-unique-subsets/

// Similar to Combination-Sum-II

class UniqueSubSetsOfArray {

    /*
    ********** Approach 1: By Constructing all the Subsets size-by-size in every step/recursion *********
    * We also sort the array to avoid duplicates, this takes O(n*log(n))
    * We generate all 2^n subsets (this takes O(2^n) time) that are of length 'n' im worst case,
      & we also add all the 2^n subsets into a unique-sub-set list. So, this takes O(n * 2^n) time

    * T.C --> O(n*log(n)) + O(2^n) +O(n * 2^n)  ~ O(n * 2^n)

    * Space Complexity:  O(2^n * k) + O(n)   ~  O(n * 2^n)
    * Space Complexity:  O(2^n * k) + O(n)   ~  O(n)    Ignoring output
    * O(2^n * k) To store every subset of average length k. (k = n in worst case)
    * O(n) for 'n' recursion stack space (recursive calls).
    * If we Ignore output ArrayList of Subsets (that are 2^n in number) into Space complexity,
    * then also Space Complexity is O(n) only due to Recursion calls
    */
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


    /*
    **************** Approach 2: Pick & Non-pick by using HashSet to avoid Duplicate ***************
    * We also sort the array to avoid duplicates, this takes O(n*log(n))
    * We generate all 2^n subsets (this takes O(2^n) time) that are of length 'n' im worst case,
      & we also add all the 2^n subsets into a unique-sub-set list. So, this takes O(n * 2^n) time

    * T.C --> O(n*log(n)) + O(2^n) +O(n * 2^n)  ~ O(n * 2^n)
    * HashSet addition takes O(1) time for its all methods

    * Space Complexity:  O(2^n * k) + O(n)  + O(n * 2^n)  ~  O(n * 2^n)
    * O(2^n * k) To store every subset of average length k. (k = n in worst case)
    * Since we are initially using a set to store the answer another O(2^n *k) is also used.
    * O(n) for 'n' recursion stack space (recursive calls).
    * Even if we don't Ignore output ArrayList of Subsets (that are 2^n in number) into Space complexity,
    * then also Space Complexity is O(2^n * k) + O(n) due to HashSet & Recursion calls
    */

    public List<List<Integer>> subsetsWithDup_(int[] arr) {
        // By sorting we ensure that every subset inside the all Unique combinations sets are in sorted fashion
        // and hence HashSet can avoid duplicates
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