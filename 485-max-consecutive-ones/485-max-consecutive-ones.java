class Solution {
    public int findMaxConsecutiveOnes(int[] arr) {
        int left = 0, right = 0;
        int maxOnesLength = 0;
        
        while (right != arr.length){
            if (arr[right] == 1){
                maxOnesLength = Math.max(maxOnesLength, right - left + 1);
                right++;
            }
            else if (arr[right] == 0)
                left = ++right;
        }
        
        return maxOnesLength;
    }
}