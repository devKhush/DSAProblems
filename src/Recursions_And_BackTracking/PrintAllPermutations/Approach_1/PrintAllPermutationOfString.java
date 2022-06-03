package Recursions_And_BackTracking.PrintAllPermutations.Approach_1;

// https://www.youtube.com/watch?v=DKCbsiDBN6c
// https://www.youtube.com/watch?v=SJ_pXT-L5IE

// https://www.tutorialspoint.com/introduction-to-backtracking

/*
- Brute Force approach finds all the possible solutions and selects desired solution per given the constraints.
- Dynamic Programming also uses Brute Force approach to find the OPTIMUM solution, either maximum or minimum.
- Backtracking also uses Brute Force approach but to find ALL the solutions.
- Solutions to the Backtracking problems can be represented as State-Space Tree.
- The constraints applied to find the solution is called Bounding function.
- Backtracking follows Depth-First Search method.
- Branch and Bound is also a Brute Force approach, which uses Breadth-First Search method.
 */

public class PrintAllPermutationOfString {
    public static void allPermutations(String[] arr){
        getAllPermutations(arr, new boolean[arr.length], 0, "");
    }

    private static void getAllPermutations(String[] arr, boolean[] visited, int elementTakenInPermutation, String currentPermutation){
        if (elementTakenInPermutation == arr.length) {
            System.out.println(currentPermutation);
            return;
        }
        for (int i=0; i<arr.length; i++){
            if (!visited[i]){
                visited[i] = true;
                getAllPermutations(arr, visited, elementTakenInPermutation+1, currentPermutation + arr[i]);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        String[] arr = {"a", "b", "c", "d"};
        allPermutations(arr);
    }
}
