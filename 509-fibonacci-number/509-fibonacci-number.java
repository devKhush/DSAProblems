class Solution {
    public int fib(int n){
        if (n == 0) return 0;

        int a = 0, b = 1;
        
        for (int i = 2; i <= n; i++){
            int temp = b;
            b = a + b;
            a = temp;
        }
         
        return b;
    }
    
    public int fib_Iterative(int n){
        if (n == 0) return 0;

        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;
         
        for (int i = 2; i <= n; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
         
        return fib[n];
    }
    
    // Memoization *******************************************************88
    int[] fib;
    public int fib_Memoization(int n){
        this.fib = new int[n + 1];
        
        return fibonacci(n);
    }
    
    public int fibonacci(int n) {
        if (n == 0 || n == 1)
            return n;
        
        if (fib[n] != 0)
            return fib[n];
        
        return fib[n] = fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    
    
    /* // Recursive Solution
     public int fibonacci(int n) {
        if (n == 0 || n == 1)
            return n;
                
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    */
}