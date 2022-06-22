class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int sumOfOddLengthSubarray = 0;
        
        for (int i = 0; i < n; i++)
             sumOfOddLengthSubarray +=  ((i + 1) * (n - i) + 1)/2  * arr[i];
        
        return sumOfOddLengthSubarray;
    }
    
    
    
    public int sumOddLengthSubarrays_BruteForce(int[] arr) {
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