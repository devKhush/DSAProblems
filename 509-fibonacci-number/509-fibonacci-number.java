class Solution {
    
    private int[] dp;
    public int fibonacci(int n){
        if (n<=1)
            return n;
        
        if (dp[n] == 0)
            dp[n] = fibonacci(n-1) + fibonacci(n-2);
        return dp[n];
    }
    
    public int fib(int n) {
        dp = new int[n+1];
        
        return fibonacci(n);
    }

    
    /*
    O(1) space solution
    public int fib(int n) {
        if (n==0)
            return 0;
        if (n==1)
            return 1;
        int a = 0;
        int b = 1;
        int sum=0;
        
        while(n>1){
            sum = a+b;
            a=b;
            b=sum;
            n--;
        }
        return sum;
    }
    */
    
    /*
    // Solution using HashMap
    
    private HashMap<Integer, Integer> fib = new HashMap<>();
    public int fib(int n) {
        if (n <= 1)
            return n;
        
        if (fib.containsKey(n))
            return fib.get(n);
        
        fib.put(n, fib(n-1)+fib(n-2));
        return fib.get(n);
    }
    */
    
    
}