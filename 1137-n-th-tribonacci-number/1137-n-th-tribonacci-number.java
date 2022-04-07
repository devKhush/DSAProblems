class Solution {
    private int[] tribonacci;
    
    public int tribonacci_func(int n) {
        if (n==0) {
            tribonacci[n] = 0;
            return 0;
        }       
        if (n==1 || n==2) {
            tribonacci[n] = 1;
            return 1;
        }
        else if (tribonacci[n] == 0){
            tribonacci[n] = tribonacci_func(n-1) +  tribonacci_func(n-2) +  tribonacci_func(n-3);
            return tribonacci[n];
        }
        return tribonacci[n];
    }
    
    public int tribonacci(int n) {
        this.tribonacci = new int[n+1]; 
        
        tribonacci_func(n);
        
        return   tribonacci[n];
    }
}