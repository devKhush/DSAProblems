package Recursions_And_BackTracking.CombinationSum_II;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/G1fRTGRxXU8
// https://takeuforward.org/data-structure/combination-sum-ii-find-all-unique-combinations/

class CombinationSum_II {
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int n = candidates.length;

        // Sorting makes easier to generate all subsequence with given sum, as repeating element
        // can be easily ignored
        mergeSort(candidates, 0, n-1, new int[n]);
                
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
            if (targetSum < currentSum + arr[i])
                break;
            
            list.add(arr[i]);
            getCombinationSum(i+1, targetSum, currentSum + arr[i], arr, answer, list);
            list.remove(list.size() -1);
        }
    }
    

    // Sorting the array
    private void mergeSort(int[] arr, int low, int high, int[] temp){
        if (low < high){
            int mid = (low + high)/2;
            mergeSort(arr, low, mid, temp);
            mergeSort(arr, mid + 1, high, temp);
            merge(arr, temp, low, high, mid);
        }
    }
    
    private void merge(int[] arr, int[] temp, int low, int high, int mid){
        int i = low, j = mid + 1, k = low;
        
        while (i <= mid && j <= high){
            if (arr[i] < arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        
        while (i <= mid)
            temp[k++] = arr[i++];
        
        while (j <= high)
            temp[k++] = arr[j++];
        
        for (int a = low; a <= high; a++)
            arr[a] = temp[a];
    }
  
}