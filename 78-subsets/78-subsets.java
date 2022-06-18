class Solution {
    // ******************************* Approach 1 ******************************* 

    public List<List<Integer>> subsets_V1(int[] arr) {
        List<List<Integer>> answer = new ArrayList<>();

        getAllSubsequences(0, arr, answer, new ArrayList<>());
        return answer;
    }
    
    private void getAllSubsequences(int index, int[] arr, List<List<Integer>> allSubSequence, ArrayList<Integer> list){
        allSubSequence.add(new ArrayList<>(list));

        for (int i = index; i < arr.length; i++){

            list.add(arr[i]);
            getAllSubsequences(i+1, arr, allSubSequence, list);
            list.remove(list.size() - 1);

        }
    }
    
    // ******************************* Approach 2 ******************************* 
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        
        getSubSequences(0, nums, new ArrayList<>(), answer);
        
        return answer;
    }
    
    public void getSubSequences(int i, int[] arr, ArrayList<Integer> list, List<List<Integer>> answer){
        if (i == arr.length){
            answer.add(new ArrayList<>(list));
            return;
        }
        
        list.add(arr[i]);
        getSubSequences(i+1, arr, list, answer);
        list.remove(list.size() -1);
        
        getSubSequences(i+1, arr, list, answer);
    }
}