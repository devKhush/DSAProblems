class Solution {
    public int longestConsecutive(int[] arr) {
        int n = arr.length;
        if (n == 0)
            return 0;
        
        Arrays.sort(arr);
        
        int longestConsecutiveSequenceLength = 1;
        
        int currSequenceLength = 1;
        
        for (int i = 1; i < n; i++){
            if (arr[i] == arr[i-1] + 1)
                currSequenceLength++;
            
            else if (arr[i] !=  arr[i-1])
                currSequenceLength = 1;
            
            longestConsecutiveSequenceLength = Math.max(longestConsecutiveSequenceLength, currSequenceLength);
        }
        return longestConsecutiveSequenceLength;
    }
}