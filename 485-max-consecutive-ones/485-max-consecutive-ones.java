class Solution {
    public int findMaxConsecutiveOnes(int[] arr) {
        int maxOnesLength = 0;
        int currOnesLength = 0;

        for (int i = 0; i < arr.length; i++){
            if (arr[i] == 1)
                currOnesLength++;
            else 
                currOnesLength = 0;
            
            maxOnesLength = Math.max(maxOnesLength, currOnesLength);
        }
        return maxOnesLength;
    }
}