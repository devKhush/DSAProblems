class Solution {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        
        double halfPower = myPow(x, n/2);
        
        if (n % 2 == 0)
            return halfPower * halfPower;
        else{
            if (n > 0)
                return halfPower * halfPower * x;
            else
                return halfPower * halfPower / x;
        }
        
    }
}