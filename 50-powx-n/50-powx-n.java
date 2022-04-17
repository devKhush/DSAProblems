class Solution {
    public double myPow(double x, int n) {
        if (n==0)
            return 1;
        
        double halfAnswer = myPow(x, n/2);
        
        if (n % 2 == 0)
            return halfAnswer * halfAnswer;
        else{
            if (n > 0)
                return x * halfAnswer * halfAnswer;
            else
                return halfAnswer * halfAnswer / x;
        }
    }
}