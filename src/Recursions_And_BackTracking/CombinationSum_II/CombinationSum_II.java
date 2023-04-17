package Recursions_And_BackTracking.CombinationSum_II;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

// https://youtu.be/G1fRTGRxXU8
// https://takeuforward.org/data-structure/combination-sum-ii-find-all-unique-combinations/


/*
Time Complexity:  O(n*log(n)) + O(2^n * k)   ~  O(2^n * k)
Reason: Assume if all the elements in the array are unique then the no. of subsequence we will get
(will be made by recursive calls) will be O(2^n). We also add the combination (whose sum is target)
to our "allUniqueCombinations" ArrayList when we reach the base case that will take O(k) time
k is the average length of the combination

Space Complexity:O(k*x)
Reason: if we have x combinations then space will be x*k where k is the average length of the combination.
 */

class CombinationSum_II {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int n = candidates.length;
        // Sorting makes easier to generate all subsequence with given sum, as repeating element
        // can be easily ignored
        Arrays.sort(candidates);
                
        List<List<Integer>> allCombinations = new ArrayList<>();
        
        getCombinationSum(0, target, 0, candidates, allCombinations, new ArrayList<>());
        return allCombinations;
    }
    
    private void getCombinationSum(int index, int targetSum, int currentSum, int[] arr, List<List<Integer>> answer, List<Integer> list){
        if (targetSum == currentSum){
            answer.add(new ArrayList<>(list));
            // Can stop the further recursion as current Sum will become greater than target Sum
            // No need of this here as this is handled by for loop's 2nd condition
            return;
        }
    
        for (int i = index; i < arr.length; i++){

            // As the combinations should be unique (no repeating combination like [1,2,2] & [1,2,2] for two repeating 2's)
            // If this element has been previously considered in the unique combinations, we can skip it
            if (i > index  &&  arr[i] == arr[i-1])
                continue;

            // Can stop the further recursion as current Sum will become greater than target Sum
            // As in later part of array, there will be much higher element (we have sorted)
            // So this condition will become true for later elements of array too, hence can be breaked
            if (targetSum < currentSum + arr[i])
                break;
            
            list.add(arr[i]);
            getCombinationSum(i+1, targetSum, currentSum + arr[i], arr, answer, list);
            list.remove(list.size() -1);
        }
    }


    /******************************* ANOTHER SOLUTION *****************************************
     */
    public List<List<Integer>> combinationSum2_(int[] arr, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(arr);

        f(0, arr, list, new ArrayList<>(), target);
        return list;
    }
    public void f(int index, int[] arr, List<List<Integer>> list, ArrayList<Integer> ls, int target){
        if (target == 0)
            list.add(new ArrayList<>(ls));

        for (int i = index; i < arr.length; i++){
            if (i != index && arr[i] == arr[i-1])
                continue;
            if (target < arr[i])
                break;

            ls.add(arr[i]);
            f(i + 1, arr, list, ls, target - arr[i]);
            ls.remove(ls.size() - 1);
        }
    }

}