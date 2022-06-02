class Solution {
    public List<List<Integer>> subsetsWithDup(int[] arr) {
        Arrays.sort(arr);
        
        List<List<Integer>> allUniqueSubsets = new ArrayList<>();
        
        getAllUniqueSubsets(0, arr, allUniqueSubsets, new ArrayList<>());
        return allUniqueSubsets;
    }
    
    private void getAllUniqueSubsets(int index, int[] arr, List<List<Integer>> answer, ArrayList<Integer> list){
        answer.add(new ArrayList<>(list));
        
        for (int i = index; i < arr.length; i++){
            
            if (i > index  && arr[i] == arr[i-1])
                continue;
            
            list.add(arr[i]);
            getAllUniqueSubsets(i+1, arr, answer, list);
            list.remove(list.size() - 1);
        }
    }
}