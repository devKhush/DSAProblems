package Strings.StringToInteger_Atoi;

// https://youtu.be/LozY2da_aLU

public class StringToInteger_Atoi {
    /******************************** Efficient Approach 1 : With long ******************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int myAtoi_V1(String s) {
        int n = s.length();
        int i = 0;
        boolean isPositive = true;

        // Skip white spaces from the starting of string
        while (i < n  &&  s.charAt(i)==' ')
            i++;

        // Get the sign, if Available
        if (i < n  &&  (s.charAt(i) == '-' || s.charAt(i) == '+')){
            isPositive = s.charAt(i) == '-' ? false : true;
            i++;
        }

        // Convert to the actual number and make sure it's not overflow
        long number = 0;
        while (i < n){
            int digit = s.charAt(i) - '0';
            if (digit < 0 || digit > 9)
                break;
            number = number*10 + digit;
            i++;

            // We can check for overflow, after adding 'digit' to the 'number' as "long" can be easily used
            // to check for Integer overflows
            if (isPositive  &&  number > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            else if (!isPositive  &&  -number < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }
        return isPositive ? (int)number : -(int)number;
    }


    /******************************** Efficient Approach 2 : Without long ******************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int myAtoi_V2(String s) {
        int n = s.length();
        int i = 0;
        boolean isPositive = true;

        // Skip white spaces from the starting of string
        while (i < n  &&  s.charAt(i)==' ')
            i++;

        // Get the sign, if Available
        if (i < n  &&  (s.charAt(i) == '-' || s.charAt(i) == '+')){
            isPositive = s.charAt(i) == '-' ? false : true;
            i++;
        }

        int number = 0;
        while (i < n){
            int digit = s.charAt(i) - '0';
            if (digit < 0 || digit > 9)
                break;

            // We should check for overflow before adding the digit
            // This is same formula "number * 10 + digit > Integer.MAX_VALUE" just moved to RHS, because
            // on LHS it may overflow
            if (number > (Integer.MAX_VALUE - digit)/10)
                return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            number = number*10 + digit;
            i++;
        }
        return isPositive ? number : -number;
    }
}
