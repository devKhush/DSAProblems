class Solution {
    
     // ************************************* Approach 2 *************************************************
 
    public List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> allPermutations = new ArrayList<>();
        
        allPermutations(0, arr, allPermutations);
        
        return allPermutations;
        
    }
    
    private void allPermutations(int index, int[] arr, List<List<Integer>> answer){
        if (index == arr.length){
            ArrayList<Integer> list = new ArrayList<>();
            for (int val : arr)
                list.add(val);
            
            answer.add(list);
        }
        
        
        for (int i = index; i < arr.length; i++){
                swap(index, i, arr);
                allPermutations(index + 1, arr, answer);
                swap(index, i, arr);
        }
    }
    
    private void swap(int i, int j, int[] arr){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}