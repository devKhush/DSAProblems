package Recursions_And_BackTracking.Combinations;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/combinations/description/

public class Combinations {
    /************************************* Recursion *******************************************
     * Time Complexity: O(exponential)
     * Space Complexity: O(n)
        * Ignoring space due to output list
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> allCombinations = new ArrayList<>();
        f(1, n, k, allCombinations, new ArrayList<>());
        return allCombinations;
    }
    private void f(int i, int n, int k, List<List<Integer>> allCombinations, ArrayList<Integer> list){
        if (list.size() == k){
            allCombinations.add(new ArrayList<>(list));
            return;
        }
        for (int j = i; j <= n; j++){
            list.add(j);
            f(j + 1, n, k, allCombinations, list);
            list.remove(list.size() - 1);
        }
    }
}
