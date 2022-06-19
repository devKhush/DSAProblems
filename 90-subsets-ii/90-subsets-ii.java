class Solution {
    
    // Approach 2: Pick & Non-pick by using HashSet to avoid Duplicate ******************************
    public List<List<Integer>> subsetsWithDup(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> allUniqueSubsets = new ArrayList<>();

        getAllSubsets(0, arr, allUniqueSubsets, new ArrayList<>());
        return allUniqueSubsets;
    }
    
    public void getAllSubsets(int index, int[] arr, List<List<Integer>> answer, ArrayList<Integer> list){
        answer.add(new ArrayList<>(list));
        
        for (int i = index; i < arr.length; i++){
            if (i > index  && arr[i] == arr[i-1])
                continue;
            list.add(arr[i]);
            getAllSubsets(i + 1, arr, answer, list);
            list.remove(list.size() - 1);
        }
    }
    
    
    
    // Approach 1: Pick & Non-pick by using HashSet to avoid Duplicate ******************************
    public List<List<Integer>> subsetsWithDup_V1(int[] nums) {
        Arrays.sort(nums);
        HashSet<ArrayList<Integer>> allSubsets = new HashSet<>();
        
        getAllSubset(0, nums, allSubsets, new ArrayList<>());
        
        return new ArrayList<>(allSubsets);
    }
    
    public void getAllSubset(int i, int[] arr, HashSet<ArrayList<Integer>> set, ArrayList<Integer> list){
        if (i == arr.length){
            set.add(new ArrayList<>(list));
            return;
        }
        
        list.add(arr[i]);
        getAllSubset(i + 1, arr, set, list);
        list.remove(list.size() - 1);
        
        getAllSubset(i + 1, arr, set, list);
    }
}