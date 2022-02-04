package NumberTheory.AllFactorsOfANumber;

import java.util.ArrayList;
import java.util.Scanner;

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


class Solution{
    static int ways = 0;

    public static void ways(int X, int Y){
        if (X<1)
            return;
        if (X==1 && Y==0) {
            ways++;
            return;
        }
        else if (X==1 && Y==1) {
            ways++;
            return;
        }
        if (X>1 && Y>=1){
            ways +=2;
            ways(X-1, Y-1);
        }
        else {
            ways++;
            ways(X-1, Y-1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < T; i++) {
            String[] input = sc.nextLine().split(" ");
            int X = sc.nextInt();
            int Y = sc.nextInt();
            //ways(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            ways(X, Y);
            System.out.println(ways);
            ways = 0;
        }
    }
}
