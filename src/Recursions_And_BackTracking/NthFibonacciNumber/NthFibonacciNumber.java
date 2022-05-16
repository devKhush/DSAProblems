package Recursions_And_BackTracking.NthFibonacciNumber;
import java.util.HashMap;

// https://youtu.be/tyB0ztf0DNY

// Tabulation -> Go from Base cases to High level cases
// Memoization -> Go from High level cases to Base cases
// Both Stores answer of bases cases to solve high level cases

//https://takeuforward.org/data-structure/dynamic-programming-introduction/

class NthFibonacciNumber {

    // Constant space solution
    public int fib_OrderOf1Space(int n) {
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

    // Array solution with both TC and SC O(n)
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

    // Solution using HashMap
    private HashMap<Integer, Integer> fib = new HashMap<>();
    public int fib_HashMapSolution(int n) {
        if (n <= 1)
            return n;

        if (fib.containsKey(n))
            return fib.get(n);

        fib.put(n, fib(n-1)+fib(n-2));
        return fib.get(n);
    }
}