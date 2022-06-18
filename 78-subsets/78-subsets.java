class Solution {
    
    public List<List<Integer>> subsets(int[] nums) {
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