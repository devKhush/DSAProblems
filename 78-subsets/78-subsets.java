class Solution {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        
        getAllSubsets(0, nums, allSubsets, new ArrayList<>());
        return allSubsets;
    }
    
    private void getAllSubsets(int index, int[] arr, List<List<Integer>> answer, ArrayList<Integer> list){
        answer.add(new ArrayList<>(list));
        
        for (int i = index; i < arr.length; i++){
            list.add(arr[i]);
            getAllSubsets(i + 1, arr, answer, list);
            list.remove(list.size() - 1);
        }
    }
    
    

    // Approach 1 ******************************************************************************
    public List<List<Integer>> subsets_V1(int[] nums) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        
        getAllSubset(0, nums, allSubsets, new ArrayList<>());
        return allSubsets;
    }
    
    public void getAllSubset(int i, int[] arr, List<List<Integer>> answer, ArrayList<Integer> list){
        if (i == arr.length){
            answer.add(new ArrayList<Integer>(list));
            return;
        }
        
        getAllSubset(i + 1, arr, answer, list);
        
        list.add(arr[i]);
        getAllSubset(i + 1, arr, answer, list);
        list.remove(list.size() - 1);
    }
}