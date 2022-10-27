class Solution {
    public int reverse_int_version(int x) {
        int reverse = 0;
        while (x != 0){
            int digit = x % 10;
            
            if (reverse > Integer.MAX_VALUE/10)
                return 0;
            if (reverse < Integer.MIN_VALUE/10)
                return 0;
            
            reverse = reverse*10 + digit;
            x /= 10;
        }
        return reverse;
    }
    
    public int reverse(int x) {
        long reverse = 0;
        while (x != 0){
            int digit = x % 10;
            reverse = reverse*10 + digit;
            x /= 10;
            
            if (reverse > Integer.MAX_VALUE)
                return 0;
            if (reverse < Integer.MIN_VALUE)
                return 0;
        }
        return (int)reverse;
    }
}