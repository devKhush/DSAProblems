class Solution {
    
    public List<Integer> majorityElement(int[] arr) {
        int n = arr.length;
        
        HashMap<Integer, Integer> count = new HashMap<>();
        List<Integer> majorityElements = new ArrayList<>();
        
        for (int num : arr){
            count.put(num, count.getOrDefault(num, 0) + 1);
            
            if (count.get(num) > n/3  &&  !majorityElements.contains(num))
                majorityElements.add(num);
        }
        
        return majorityElements;
    }
    
    // Brute force *********************************************************************
    public List<Integer> majorityElement_BruteForce(int[] arr) {
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