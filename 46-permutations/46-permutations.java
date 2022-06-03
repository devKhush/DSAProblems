class Solution {
    public List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> allPermutations = new ArrayList<>();
        
        allPermutations(arr, 0, new boolean[arr.length], new ArrayList<>(), allPermutations);
        
        return allPermutations;
    }
    
    private void allPermutations(int[] arr, int elementPicked, boolean[] visited, ArrayList<Integer> list, List<List<Integer>> answer){
        if (elementPicked == arr.length){
            answer.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i < arr.length; i++){
            if (!visited[i]){
                
                visited[i] = true;
                list.add(arr[i]);
                
                allPermutations(arr, elementPicked + 1, visited, list, answer);

                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}