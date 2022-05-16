package Recursions_And_BackTracking.NthFibonacciNumber;
import java.util.HashMap;

class NthFibonacciNumber {

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

    private HashMap<Integer, Integer> fib = new HashMap<>();
    
    public int fib(int n) {
        if (n <= 1)
            return n;
        
        if (fib.containsKey(n))
            return fib.get(n);
        
        fib.put(n, fib(n-1)+fib(n-2));
        return fib.get(n);
    }
}