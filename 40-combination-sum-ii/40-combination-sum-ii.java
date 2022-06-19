class Solution {
    
    public List<List<Integer>> combinationSum2(int[] arr, int target) {
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        
        List<List<Integer>> allUniqueCombinations = new ArrayList<>();
        
        getAllUniqueCombinations(0, arr, target, allUniqueCombinations, new ArrayList<>());
        return allUniqueCombinations;
    }
    
    private void getAllUniqueCombinations(int index, int[] arr, int target, List<List<Integer>> answer, ArrayList<Integer> list){

        if (target == 0)
            answer.add(new ArrayList<>(list));
        
        for (int i = index; i < arr.length; i++){
            if (i > index && arr[i] == arr[i-1])
                continue;
            
            if (arr[i] <= target){
                list.add(arr[i]);
                getAllUniqueCombinations(i + 1, arr, target - arr[i], answer, list);
                list.remove(list.size() - 1);
            }
        }
    }
    
   
    
    
    private void mergeSort(int[] arr, int low, int high, int[] temp){
        if (low < high){
            int mid = (low + high)/2;
            mergeSort(arr, low, mid, temp);
            mergeSort(arr, mid + 1, high, temp);
            merge(arr, temp, low, high, mid);
        }
    }
    
    private void merge(int[] arr, int[] temp, int low, int high, int mid){
        int i = low, j = mid + 1, k = low;
        
        while (i <= mid && j <= high){
            if (arr[i] < arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }
        
        while (i <= mid)
            temp[k++] = arr[i++];
        
        while (j <= high)
            temp[k++] = arr[j++];
        
        for (int a = low; a <= high; a++)
            arr[a] = temp[a];
    }
  
}