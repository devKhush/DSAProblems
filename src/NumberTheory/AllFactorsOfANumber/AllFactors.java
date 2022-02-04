package NumberTheory.AllFactorsOfANumber;

import java.util.ArrayList;

public class AllFactors {
    public int[] allFactors(int A) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i=1; i<=A; i++){
            if (A%i==0)
                arrayList.add(i);
        }
        int[] allFactor = new int[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            allFactor[i] = arrayList.get(i);
        }
        return allFactor;
    }
}
