package BinarySearch.AggresiveCows;
import java.util.Arrays;
import java.util.Scanner;

// https://www.youtube.com/watch?v=wSOfYesTBRk

public class AggressiveCows {

    // Brute Force time complexity = O(Cows * N)        in worst case
    // Brute Force time complexity = O(Cows * log(N))   in worst case

    private static int aggressiveCows(int n, int Cows, int[] stallPositions){
        Arrays.sort(stallPositions);

        // Low set to 1 as max. possible minimum distance can be 1
        // Reason: since no cows can be place in same stall
        int low = 1;

        // High can be set to below value, as in best case cows can be 2
        // so max. possible distance is below value
        int high = stallPositions[n-1] - stallPositions[0];

        // maximum difference in distance b/w stalls
        int maximumDistance = 0;

        while (low <= high){
            int mid = (low + high)/2;    // Recall same as dividing by 2

            if (canPlaceCows(n, Cows, stallPositions, mid)){
                maximumDistance = Math.max(maximumDistance, mid);
                low = mid + 1;
            }
            else
                high = mid - 1;
        }
        return maximumDistance;
    }

    private static boolean canPlaceCows(int n, int cows, int[] stallPositions, int distance) {
        int recentCowPlacedPosition = stallPositions[0];
        int cowsCount = 1;

        for (int i=1; i<n; i++){
            if (stallPositions[i] - recentCowPlacedPosition >= distance){
                recentCowPlacedPosition = stallPositions[i];
                cowsCount++;
            }
            if (cows == cowsCount)
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int N = 5, C = 3;
        int[] arr = {1,2,8,4,9};

//        input();
    }

    public static void input() {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int j = 1; j<=t; j++) {
            int n = sc.nextInt();
            int c = sc.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();

            System.out.println(aggressiveCows(n, c, arr));
        }
    }
}
