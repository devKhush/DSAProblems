package NumberTheory.AllFactorsOfANumber;

import java.util.ArrayList;
import java.util.Arrays;

public class AllFactors {
    public int[] allFactors(int A) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=1; i<=A; i++){
            if (A%i==0)
                arrayList.add(i);
        }
        System.out.println(arrayList);
        int[] allFactor = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            allFactor[i] = arrayList.get(i);
        }
        return allFactor;
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println(Arrays.toString(new AllFactors().allFactors(n)));
    }
}
