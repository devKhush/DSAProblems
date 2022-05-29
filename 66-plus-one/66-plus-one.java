class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 0, sum;
        
        for (int i = n-1; i >= 0; i--){
            if (i == n-1)
                sum = digits[i] + 1;
            else
                sum = digits[i] + carry;
            
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        
        if (carry == 0)
            return digits;
        
        else{
            int[] answer = new int[n+1];
            answer[0] = carry;
            
            for (int i = 0; i < n; i++)
                answer[i+1] = digits[i];
            return answer;
        }
    }
}