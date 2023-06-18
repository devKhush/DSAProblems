package DynamicProgramming.DP_on_Arrays.MakeArrayStrictlyIncreasing;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

// Similar to Knapsack Problems
// https://youtu.be/5qAq9vlSFjs
// https://leetcode.com/problems/make-array-strictly-increasing/description/
// https://leetcode.com/problems/make-array-strictly-increasing/editorial/

public class MakeArrayStrictlyIncreasing {
    /************************************ DP + Sorting + Binary_Search *********************************
     * Intuition:
        * At every index, do either replace or not_replace
        * Watch video
        * Not-Replace works only when current value is greater than previous value.
        * Replace when current value is greater or smaller than previous value.
        * Maintain DP HashTable, prev value

     * Time Complexity: O(m*n*log(n))
        * m -> size of arr1
        * n -> size of arr2
        * prev parameter can have atmost n states
        * two states of recursion
     * Space Complexity: O(m+n) + O(m*n)
        * Recursion stack space of O(m+n)
        * two states
     */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {
        // Sort arr2 to fins the value just greater than current value using binary search
        Arrays.sort(arr2);

        int answer = dfs(0, Integer.MIN_VALUE, arr1, arr2);
        return answer < 1e9 ? answer : -1;
    }

    HashMap<Pair<Integer, Integer>, Integer> dp = new HashMap<>();
    private int dfs(int i, int prev, int[] arr1, int[] arr2) {
        if (i == arr1.length)
            return 0;
        if (dp.containsKey(new Pair<>(i, prev)))
            return dp.get(new Pair<>(i, prev));

        // If arr1[i] is already greater than prev, we can leave it be.
        int notReplace = arr1[i] > prev ? dfs(i + 1, arr1[i], arr1, arr2) : (int)1e9;

        // Find a replacement with the smallest value in arr2.
        // Replace arr1[i], with a cost of 1 operation.
        int idx = upper_bound(arr2, prev);
        int replace = idx < arr2.length ? 1 + dfs(i + 1, arr2[idx], arr1, arr2) : (int)1e9;

        int minOperations = Math.min(replace, notReplace);
        dp.put(new Pair<>(i, prev), minOperations);
        return Math.min(replace, minOperations);
    }

    private static int upper_bound(int[] arr2, int val) {
        int low = 0, high = arr2.length - 1;
        while (low <= high){
            int mid = low + ((high - low)>>1);
            if (arr2[mid] <= val)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    static class Pair<A, B>{
        A a; B b;
        public Pair(A a, B b){
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o){
            if (this.getClass() != o.getClass())
                return false;
            Pair<A, B> p = (Pair)o;
            return this.a.equals(p.a) && this.b.equals(p.b);
        }

        @Override
        public int hashCode(){
            return Objects.hash(a, b);
        }
    }
}
