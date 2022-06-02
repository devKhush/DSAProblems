class Solution {
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