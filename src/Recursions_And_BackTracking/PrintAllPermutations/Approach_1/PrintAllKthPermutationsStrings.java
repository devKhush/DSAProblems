package Recursions_And_BackTracking.PrintAllPermutations.Approach_1;

public class PrintAllKthPermutationsStrings {
    public static void printAllKthPermutations(String[] arr, int k){
        getAllKthPermutations(arr, new boolean[arr.length], k, "", 0);
    }

    private static void getAllKthPermutations(String[] arr, boolean[] visited, int k, String currentPermutation, int elementIncluded){
        if (elementIncluded == k){
            System.out.println(currentPermutation);
            return;
        }

        for (int i=0; i<arr.length; i++){
            if (!visited[i]){
                visited[i] = true;
                getAllKthPermutations(arr, visited, k, currentPermutation + arr[i], elementIncluded+1);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        String[] arr = {"a", "b", "c", "d"};

        printAllKthPermutations(arr, 4);
    }
}
