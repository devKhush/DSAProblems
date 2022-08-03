class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        
        int i = a.length() - 1, j = b.length() - 1;        
        int carry = 0;

        while (i >= 0 || j >= 0 || carry == 1){
            int sum = 0;
            sum += carry;
            
            if (i >= 0) 
                sum += a.charAt(i--) - '0';
            if (j >= 0)
                sum += b.charAt(j--) - '0';

            sb.append(sum % 2);
            carry = sum / 2;
        }
        return sb.reverse().toString();
    }
    
    
    // String Concatenation is usually Too Slow
    public String addBinary_Brute(String a, String b) {
        String binarySum = "";

        int n = a.length(), m = b.length();        
        int i = n - 1, j = m - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry == 1){
            int sum = 0;
            sum += carry;
            
            if (i >= 0) 
                sum += a.charAt(i--) - '0';
            if (j >= 0)
                sum += b.charAt(j--) - '0';
            
            binarySum = (sum % 2) + binarySum;
            carry = sum / 2;
        }
        return binarySum;
    }
}