class Solution {
    
    private List<List<Integer>> allCombinations = new ArrayList<>();
    
    private void getAllCombinationSums(int index, int target, int currentSum, int[] arr, ArrayList<Integer> list){
        if (currentSum > target)
            return;
        
        if (index == arr.length){
            if (currentSum == target)
                allCombinations.add(new ArrayList<>(list));
            return;
        }
        
                
        // Picking the current element again
        currentSum += arr[index];
        list.add(arr[index]);
        getAllCombinationSums(index, target, currentSum, arr, list);
        list.remove((Object) arr[index]);
        currentSum -= arr[index];
        
        // Not Picking the current element
        getAllCombinationSums(index + 1, target, currentSum, arr, list);

    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        getAllCombinationSums(0, target, 0, candidates, new ArrayList<>());
        
        return allCombinations;
    }
}