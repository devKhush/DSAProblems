import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Instantiate an object of HashSet
        HashSet<ArrayList> set = new HashSet<>();

        // create ArrayList list1
        ArrayList<Integer> list1 = new ArrayList<>();

        // create ArrayList list2
        ArrayList<Integer> list2 = new ArrayList<>();

        // Add elements using add method
        list1.add(1);
        list1.add(2);
        list2.add(2);
        list2.add(1);

        System.out.println(list1);
        System.out.println(list2);

        set.add(list1);
        set.add(list2);

        // print the set size to understand the
        // internal storage of ArrayList in Set
        System.out.println(set.size());
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




