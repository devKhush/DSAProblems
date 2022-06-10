class Solution {
    public int findMaxLength(int[] arr) {
        int maxLen = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        
        for (int i = 0; i < arr.length; i++){
            sum = arr[i] == 1 ? sum + 1 : sum - 1;
            
            if (sum == 0)
                maxLen = i + 1;
            
            else if (map.containsKey(sum))
                maxLen = Math.max(maxLen, i - map.get(sum));
            
            else
                map.put(sum, i);
        }
        return maxLen;
    }
}