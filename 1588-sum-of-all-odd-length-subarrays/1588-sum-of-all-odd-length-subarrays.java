class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int sumOfOddLengthSubarray = 0;
        
        for (int i = 0; i < arr.length; i++){
            int subArraySum = 0;
            
            for (int j = i; j < arr.length; j++){
                subArraySum += arr[j];
                
                if ((j-i+1) % 2 == 1)
                    sumOfOddLengthSubarray += subArraySum;
            }     
        }
        return sumOfOddLengthSubarray;
    }
}