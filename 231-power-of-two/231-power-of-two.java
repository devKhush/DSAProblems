class Solution {
    public boolean isPowerOfTwo(int n) {
        
        if (n <= 0)
            return false;
        
        while (n != 1){
            
            int remainder = n % 2;
            
            if (remainder == 1)
                return false;
            
            n /= 2;
        }
        
        return n == 1;
    }
}