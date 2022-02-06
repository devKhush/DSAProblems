package NumberTheory.Primes;

// https://leetcode.com/problems/count-primes/
// https://www.youtube.com/watch?v=nDPo9hsDNvU

public class CountPrimes {

    // BRUTE FORCE SOLUTION
    public boolean isPrime(int n){
        if (n==2)
            return true;
        if (n%2==0)
            return false;
        for (int i = 2; i*i <= n; i++) {
            if (n%i == 0)
                return false;
        }
        return true;
    }

    public int countPrimesBruteForce(int n) {
        if (n <= 2)
            return 0;
        int primeCount = 0;
        for (int i = 2; i < n ; i++) {
            if (isPrime(i)) {
                primeCount++;
            }
        }
        return primeCount;
    }


    // Efficient
    public int countPrimes(int n){
        boolean[] primes = new boolean[n];
        for (int i = 2; i <n; i++) {
            primes[i] = true;
        }

        for (int i = 2; i*i <= n; i++) {
            if (primes[i]){
                for (int j = i*i; j<n ; j+=i) {
                    primes[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i<n ; i++) {
            if (primes[i])
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(new CountPrimes().countPrimes(n));
    }
}
