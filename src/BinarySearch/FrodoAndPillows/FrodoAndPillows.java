package BinarySearch.FrodoAndPillows;

import java.util.Scanner;

public class FrodoAndPillows {

    public static int getSum(int n){
        return n * (n+1) / 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        int low = 1, high = m;
        int leftLength = k - 1;
        int rightLength = n - k;

        int maxPillowAtKth = 0;

        while (low <= high){
            int midPillow = (low + high)/2;

            int leftPillowSum = getSum(midPillow-1) - (midPillow-1 > leftLength ? getSum(midPillow-1 - leftLength) : -(leftLength - midPillow +1));
            int rightPillowSum = getSum(midPillow-1) - (midPillow-1 > rightLength ? getSum(midPillow-1 - rightLength) : -(rightLength - midPillow + 1));

            if (leftPillowSum + rightPillowSum + midPillow <= m){
                maxPillowAtKth = midPillow;
                low = midPillow + 1;
            }
            else
                high = midPillow - 1;
        }

        System.out.println(maxPillowAtKth);
    }
}
