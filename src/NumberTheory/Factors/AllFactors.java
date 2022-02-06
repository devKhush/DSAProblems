package NumberTheory.Factors;

import java.util.ArrayList;

public class AllFactors {
    // BRUTE FORCE In O(n) Time
    public int[] allFactors_BruteForce(int A) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i <= A/2; i++) {
            if (A % i == 0)
                arrayList.add(i);
        }
        arrayList.add(A);
        int[] allFactor = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            allFactor[i] = arrayList.get(i);
        }
        return allFactor;
    }



    // EFFICIENT
    public ArrayList<Integer> allFactors(int n) {
        ArrayList<Integer> factorList = new ArrayList<>();

        for (int i = 1; i*i <= n ; i++) {
            if (n%i == 0)
                factorList.add(i);
        }
        for (int i = factorList.size()-1; i>=0; i--) {
            int factor = factorList.get(i);
            if (factor != n/factor)
                factorList.add(n/factorList.get(i));
        }
        return factorList;
    }



    public static void main(String[] args) {
        int n = 100;
        System.out.println(new AllFactors().allFactors(n));

    }
}
