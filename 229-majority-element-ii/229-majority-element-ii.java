class Solution {
    public List<Integer> majorityElement(int[] arr) {
        int n = arr.length;
        
        List<Integer> majorityElements = new ArrayList<>();
        
        for (int i = 0; i < n; i++){
            int currCount = 0;
            for (int j = i; j < n; j++){
                if (arr[i] == arr[j])
                    currCount++;
            }
            
            if (currCount > n/3  &&  !majorityElements.contains(arr[i]))
                majorityElements.add(arr[i]);
        }
        
        return majorityElements;
    }
}