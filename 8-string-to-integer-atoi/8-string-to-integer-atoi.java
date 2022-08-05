class Solution {
    public int myAtoi(String s) {
        long number = 0;
        int sign = +1;
        int i = 0, n = s.length();
        
        if (n == 0) return 0;
        
        while (i < n  &&  s.charAt(i) == ' ')
            i++;
        
        if (i < n  && (s.charAt(i) == '-' || s.charAt(i) == '+')){
            sign = s.charAt(i) == '+' ? +1 : -1;
            i++;
        }

        
        while (i < n){
            int digit = s.charAt(i) - '0';
            
            if (digit < 0 || digit > 9)
                break;
            
            number = 10L * number + digit;
            i++;

            if (sign == -1  && -number <= Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            else if (number > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
        }
        return (int) (sign * number);
    }
}