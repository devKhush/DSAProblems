class Solution {
    public List<List<Integer>> combinationSum(int[] arr, int target) {
        List<List<Integer>> allCombinations = new ArrayList<>();
        
        combinationSum(0, target, arr, new ArrayList<>(), allCombinations);
        return allCombinations;
    }
    
    public void combinationSum(int i, int target, int[] arr, ArrayList<Integer> list, List<List<Integer>> answer){
        if (target == 0 || i == arr.length){
            if (target == 0)
                answer.add(new ArrayList<>(list));
            return;
        }
        
        if (target >= arr[i]){
            list.add(arr[i]);
            combinationSum(i, target - arr[i], arr, list, answer);
            list.remove(list.size() - 1);
        }
        
        combinationSum(i + 1, target, arr, list, answer);
    }
}