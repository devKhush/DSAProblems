class Solution {
    
    // Approach 1: Pick & Non-pick by using HashSet to avoid Duplicate ******************************
    public List<List<Integer>> subsetsWithDup(int[] nums) {
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