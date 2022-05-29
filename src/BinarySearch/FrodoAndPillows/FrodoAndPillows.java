package BinarySearch.FrodoAndPillows;
import java.util.Scanner;

// https://codeforces.com/problemset/problem/760/B
// https://youtu.be/yAEl0PKB6EA

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
            // Assume a Current pillows that can be placed at kth position
            int midPillow = (low + high)/2;

            // Count the no. of pillows that can be place on left side keeping 'midPillow' on kth position
            // We manually give one pillow to each Bed, if we pillow on any bed becomes 0 (as second return value in ternary operator)
            // By Doing this, below if condition will always evaluate to true
            int leftPillowSum = getSum(midPillow-1) - (midPillow-1 > leftLength ? getSum(midPillow-1 - leftLength) : -(leftLength - midPillow +1));

            // Count the no. of pillows that can be place on right side keeping 'midPillow' on kth position
            // We manually give one pillow to each Bed, if we pillow on any bed becomes 0 (as second return value in ternary operator)
            // By Doing this, below if condition will always evaluate to true
            int rightPillowSum = getSum(midPillow-1) - (midPillow-1 > rightLength ? getSum(midPillow-1 - rightLength) : -(rightLength - midPillow + 1));

            // If this current pillow can be placed at kth position, then consider it & go to find next
            // higher pillows that can be place at kth position
            if (leftPillowSum + rightPillowSum + midPillow <= m){
                maxPillowAtKth = midPillow;
                low = midPillow + 1;
            }
            // If it can't be placed we have used grater no. of pillows than given to us,
            // so decrease our range of binary search
            else
                high = midPillow - 1;
        }

        System.out.println(maxPillowAtKth);
    }
}
