package Recursions;

import java.util.HashMap;

public class NthTribonacciNumber {
    public int tribonacci__BruteForce(int n){
        if (n==0)
            return 0;
        if (n==1 || n==2)
            return 1;
        else
            return tribonacci__BruteForce(n-1)+tribonacci__BruteForce(n-2)+tribonacci__BruteForce(n-3);
    }

    public int tribonacci_Iterative(int n) {
        int a=0, b=1, c=1;
        if (n==0)
            return a;
        if (n==1||n==2)
            return b;

        int sum = 0;
        while (n>2){
            sum = a+b+c;
            a = b;
            b = c;
            c = sum;
            n--;
        }
        return sum;
    }

    // memory optimization
    private HashMap<Integer, Integer> fibonacciPairs = new HashMap<>();
    public int tribonacci(int n) {
        if (n==0)
            return 0;
        if (n==1 || n==2)
            return 1;
        if (!fibonacciPairs.containsKey(n))
            fibonacciPairs.put(n, tribonacci(n-1)+tribonacci(n-2)+tribonacci(n-3));
        return fibonacciPairs.get(n);
    }
}
