package Recursions_And_BackTracking.NthFibonacciNumber;

import java.util.HashMap;

//https://takeuforward.org/data-structure/dynamic-programming-introduction/

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

    // memory optimization p1
    private HashMap<Integer, Integer> fibonacciPairs = new HashMap<>();
    public int tribonacci_UsingHashMap(int n) {
        if (n==0)
            return 0;
        if (n==1 || n==2)
            return 1;
        if (!fibonacciPairs.containsKey(n))
            fibonacciPairs.put(n, tribonacci_UsingHashMap(n-1)+ tribonacci_UsingHashMap(n-2)+ tribonacci_UsingHashMap(n-3));
        return fibonacciPairs.get(n);
    }

    // Using Arrays
    public int tribonacci_UsingArray(int n) {
        if (n==0) return 0;
        if (n==1 || n==2) return 1;

        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 1;

        for (int i=3; i <= n; i++)
            arr[i] = arr[i-1] + arr[i-2] + arr[i-3];

        return arr[n];
    }
}
