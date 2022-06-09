class Solution {
    
    public List<List<Integer>> fourSum(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        
        List<List<Integer>> allQuadruplets = new ArrayList<>();
        
        
        for (int i = 0; i < n; i++){
            
            if (i > 0  && arr[i] == arr[i-1])
                continue;
            
            for (int j = i + 1; j < n; j++){
                
                if (j > i + 1  &&  arr[j] == arr[j-1])
                    continue;
                
                int low = j + 1; 
                int high = n - 1;
                
                while (low < high){
                    if (arr[i] + arr[j] + arr[low] + arr[high] == target){
                        ArrayList<Integer> currQuadruplets = new ArrayList<>();
                        
                        currQuadruplets.add(arr[i]);
                        currQuadruplets.add(arr[j]);
                        currQuadruplets.add(arr[low]);
                        currQuadruplets.add(arr[high]);
                        
                        allQuadruplets.add(currQuadruplets);
                        // System.out.println(allQuadruplets);
                        
                        while (low + 1 < high  &&  arr[low] == arr[low + 1])
                            low++;
                        
                        while (high - 1 > low  &&  arr[high] == arr[high - 1])
                            high--;
                        low++;
                    }
                    
                    if (arr[i] + arr[j] + arr[low] + arr[high] > target)
                        high--;
                    else if (arr[i] + arr[j] + arr[low] + arr[high] < target)
                        low++;
                }
            }
        }
        
        return allQuadruplets;
    }
}