package DynamicProgramming.DP_on_Arrays.FibonacciNumbers;

// https://youtu.be/tyB0ztf0DNY
//https://takeuforward.org/data-structure/dynamic-programming-introduction/

// Tabulation -> Go from Base cases to High level cases
// Memoization -> Go from High level cases to Base cases
// Both Stores answer of bases cases to solve high level cases

class FibonacciNumbers {
    // Recursive Solution ****************************************************************************
    public int fibonacci_Recursive(int n) {
        if (n == 0 || n == 1)
            return n;

        return fibonacci_Recursive(n - 1) + fibonacci_Recursive(n - 2);
    }


    // Memoization *********************************************************************************
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

    // Tabulation **************************************************************************************
    public int fib_Tabulation(int n){
        if (n == 0) return 0;

        int[] fib = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= n; i++)
            fib[i] = fib[i - 1] + fib[i - 2];

        return fib[n];
    }

    // Space Optimization **************************************************************************
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
}