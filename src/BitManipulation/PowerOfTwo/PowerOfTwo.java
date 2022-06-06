package BitManipulation.PowerOfTwo;

class PowerOfTwo {

    // Iterative Solution
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

    // ********************************** Recursive Solution **********************************
    private boolean isPowerOfTwo_(int n){
        if (n <= 0)
            return false;

        if (n == 1)
            return true;

        if (n % 2 == 1)
            return false;

        return isPowerOfTwo_(n/2);
    }
}