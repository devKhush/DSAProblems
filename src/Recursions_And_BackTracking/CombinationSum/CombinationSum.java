package Recursions_And_BackTracking.CombinationSum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

// Pre-req --> Sub-Sequence of the array

// https://youtu.be/OyZFFqQtu98
// https://takeuforward.org/data-structure/combination-sum-1/

/*
If we add one OR condition in the base condition as if(ind == arr.size() || target == 0),
keeping rest of the code same, we get the right answer but in the less recursive calls.
Because sometimes we get the target before reaching the last index, so once we get the target
we need to stop at that moment and no need to check for further elements. As it's OR, once the
base condition is satisfied, again inside that we need to check whether (target == 0) and confirm.

NOTE: THIS IDEA WILL WORK ONLY WHEN THE ELEMENT OF THE ARRAY ARE POSITIVE, IN CASE OF NEGATIVE ELEMENTS OR ZEROS
ELEMENTS, THIS IDEA WILL NOT WORK. (and hence "target == 0" must go inside the base case 'if' condition)
 */

/*
Time Complexity: O(2^t * k)     where t is the target, k is the average length of each subset
Reason: Assume if you were not allowed to pick a single element multiple times,
every element will have a couple of options: 'pick' or 'not pick' which is 2^n different recursion calls,
also assuming that the average length of every combination generated is k.
(to put length k data structure into another data structure)
Why not (2^n) but (2^t) (where n is the size of an array)?
Assume that there is 1 and the target you want to reach is 10.
So, 10 times you can “pick or not pick” an element.

Space Complexity: O(k*x), k is the average length of each combination and x is the no. of combinations
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
