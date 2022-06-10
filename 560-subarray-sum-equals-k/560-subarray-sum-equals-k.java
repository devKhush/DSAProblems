class Solution {
    public int subarraySum(int[] arr, int k) {
        int subarraysWithSumK = 0;
        int prefixSum = 0;
        
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++){
            prefixSum += arr[i];
            
            if (prefixSum == k)
                subarraysWithSumK++;
            
            if (prefixSumMap.containsKey(prefixSum - k))
                subarraysWithSumK += prefixSumMap.get(prefixSum - k);
            
            prefixSumMap.put(prefixSum, prefixSumMap.getOrDefault(prefixSum, 0) + 1);
        }
        
        return subarraysWithSumK;
    }
}