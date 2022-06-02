package Recursions_And_BackTracking.CombinationSum;
import java.util.ArrayList;
import java.util.List;

// Pre-req --> Sub-Sequence of the array

// https://youtu.be/OyZFFqQtu98
// https://takeuforward.org/data-structure/combination-sum-1/

/*
If we add one OR condition in the base condition as if(ind == arr.size() || target == 0),
keeping rest of the code same, we get the right answer but in the less recursive calls.
Because sometimes we get the target before reaching the last index, so once we get the target
we need to stop at that moment and no need to check for further elements. As it's OR, once the
base condition is satisfied, again inside that we need to check whether (target == 0) and confirm.
 */

public class CombinationSum {
    private final List<List<Integer>> allCombinations = new ArrayList<>();

    private void getAllCombinationSums(int index, int target, int currentSum, int[] arr, ArrayList<Integer> list){

        if (index == arr.length || currentSum == target){
            if (currentSum == target)
                allCombinations.add(new ArrayList<>(list));
            return;
        }

        if (target - currentSum >= arr[index]){
            // Picking the current element again only if the
            // arr[index] adds up to currenSum is less tah the target Sum, it is greater, then we shouldn't
            // perform on same element
            currentSum += arr[index];
            list.add(arr[index]);
            getAllCombinationSums(index, target, currentSum, arr, list);
            list.remove(list.size() - 1);
            currentSum -= arr[index];
        }

        // Not Picking the current element
        getAllCombinationSums(index + 1, target, currentSum, arr, list);

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        getAllCombinationSums(0, target, 0, candidates, new ArrayList<>());

        return allCombinations;
    }
}
