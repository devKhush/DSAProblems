package Recursions_And_BackTracking.PrintAllPermutations.Approach_1;
import java.util.ArrayList;
import java.util.List;

// https://youtu.be/YK78FU5Ffjw
// https://leetcode.com/problems/permutations/
// https://takeuforward.org/data-structure/print-all-permutations-of-a-string-array/

class AllPermutationsOfArray {

    // ********************************** Approach 1 **********************************
    // Count of nth permutations of array of size 'n' = n!/(n-r)! = n! / 0! = n!

    // T.C --> O(permutation of array) + O(Adding permutations into arraylist)
    // T.C --> O(n * n!)
    // O(n * n!) due to adding of all 'n!' permutations of size 'n' into ArrayList

    // S.C --> O(Recursion stack space) + O(Visited Array) + O(Dummy ArrayList) + O(Permutations array)
    // S.C. --> O(n) + O(n) + O(n) + O(n!) = O(n)    if we ignore permutations array


    public List<List<Integer>> permute(int[] arr) {
        List<List<Integer>> allPermutations = new ArrayList<>();

        allPermutations(arr, 0, new boolean[arr.length], new ArrayList<>(), allPermutations);

        return allPermutations;

    }

    private void allPermutations(int[] arr, int elementPicked, boolean[] visited, ArrayList<Integer> list, List<List<Integer>> answer){
        if (elementPicked == arr.length){
            answer.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < arr.length; i++){
            if (!visited[i]){

                visited[i] = true;
                list.add(arr[i]);

                allPermutations(arr, elementPicked + 1, visited, list, answer);

                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2};

        System.out.println(new AllPermutationsOfArray().permute(arr));
    }
}


