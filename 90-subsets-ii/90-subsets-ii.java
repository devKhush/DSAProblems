class Solution {
    
    // *************************************** Approach 1 ***************************************
    public List<List<Integer>> subsetsWithDup_(int[] arr) {
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
    
    
    // *************************************** Approach 2 ***************************************
    public List<List<Integer>> subsetsWithDup(int[] arr) {
        Arrays.sort(arr);
        
        HashSet<ArrayList<Integer>> set = new HashSet<>();
        List<List<Integer>> allUniqueSubsets = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        
        getAllSubsets(0, arr, list, set);

        // for (ArrayList<Integer> li : set)
        //     allUniqueSubsets.add(li);
        
                allUniqueSubsets.addAll(set);

        
        return allUniqueSubsets;
    }
    
    private void getAllSubsets(int i, int[] arr, ArrayList<Integer> list, HashSet<ArrayList<Integer>> set){
        if (i == arr.length){
            set.add(new ArrayList<>(list));
            return;
        }
        
        getAllSubsets(i+1, arr, list, set);
        
        list.add(arr[i]);
        getAllSubsets(i+1, arr, list, set);
        list.remove(list.size() - 1);
    }

}