class Solution {
    public double myPow(double x, int n) {
        long N = Math.abs((long)n);
        double power = 1; 

        while (N != 0){
            
            if (N % 2 == 0){
                x = x * x;
                N /= 2;
            }
            else{
                power = power * x;
                N--;
            }
        }
        
        return n >= 0 ? power : 1/power;
        
    }
}