class Solution {
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int n = candidates.length;
        
        mergeSort(candidates, 0, n-1, new int[n]);
                
        List<List<Integer>> allCombinations = new ArrayList<>();
        
        getCombinationSum(0, target, 0, candidates, allCombinations, new ArrayList<>());
        
        return allCombinations;
    }
    
    private void getCombinationSum(int index, int targetSum, int currentSum, int[] arr, List<List<Integer>> answer, List<Integer> list){
        
        if (targetSum == currentSum){
            answer.add(new ArrayList<>(list));
            return;
        }
    
        for (int i = index; i < arr.length; i++){
            if (i > index  &&  arr[i] == arr[i-1])
                continue;
            if (targetSum < currentSum + arr[i])
                break;
            
            list.add(arr[i]);
            getCombinationSum(i+1, targetSum, currentSum + arr[i], arr, answer, list);
            list.remove(list.size() -1);
        }
    }
    
    
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