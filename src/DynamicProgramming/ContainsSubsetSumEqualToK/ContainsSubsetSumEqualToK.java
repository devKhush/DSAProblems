package DynamicProgramming.ContainsSubsetSumEqualToK;

// https://youtu.be/fWX9xDmIzRI
// https://takeuforward.org/data-structure/subset-sum-equal-to-target-dp-14/

public class ContainsSubsetSumEqualToK {

    // ************************************** Recursion  **************************************

    // T.C --> O(2^N)       In worst case, we may find last subsequences as the required one
    // S.C --> O(N)         Recursion Stack space

    private Boolean subSetSumEqualK(int[] arr, int K){
        return containsSubsetSumEqualK(arr.length -1 , K, arr);
    }

    private boolean containsSubsetSumEqualK(int index, int target, int[] arr){
        if (target == 0)
            return true;

        if (index == 0)
            return arr[0] == target;

        boolean foundByPickingCurrElement = false;
        if (arr[index] <= target)
            foundByPickingCurrElement = containsSubsetSumEqualK(index-1, target - arr[index], arr);

        if (foundByPickingCurrElement)
            return true;

        boolean foundByNotPickingCurrElement = containsSubsetSumEqualK(index-1, target, arr);

        return foundByNotPickingCurrElement;
    }
}