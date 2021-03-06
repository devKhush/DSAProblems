import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }
}

class Solution{
    public static int solve(int n, int [] arr, int X, int K) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> (b-a));

//        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < n-1; i++) {
//            max = Math.max(max, arr[i + 1] - arr[i]);
//        }
//        System.out.println(max);

        for (int i = 0; i < K  &&  i < n-1; i++)
            maxHeap.add(arr[i+1] - arr[i]);

        for (int i = K; i<n-1; i++){
            if (arr[i+1] - arr[i] > maxHeap.peek()){
                maxHeap.remove();
                maxHeap.add(arr[i+1] - arr[i]);
            }
        }
        // System.out.println(maxHeap.size());
        System.out.println(maxHeap);

        int cityReached = 1;

        for (int i = 0; i < n-1; i++){

            if (maxHeap.contains(arr[i+1] - arr[i])  &&  K > 0){
                maxHeap.remove(arr[i+1] - arr[i]);
                K--;
                cityReached++;
            }

            else if (X > 0  && arr[i+1] - arr[i] <= X){
                X = X - (arr[i+1] - arr[i]);
                cityReached++;
            }

            else if (K > 0){
                K--;
                cityReached++;
            }
            else
                break;
        }
        System.out.println(X);
        System.out.println(K);

        return cityReached;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int x = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(solve(n, arr, x, k));
    }
}




