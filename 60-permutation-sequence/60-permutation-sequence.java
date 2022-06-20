class Solution {
    public String getPermutation(int n, int k) {
        int previousFactorial = 1;
        ArrayList<Integer> number = new ArrayList<>();
        
        for (int i = 1; i <= n; i++){
            number.add(i);
            
            if (i != n)
                previousFactorial *= i;
        }
        
        k--;
        String kthPermutation = "";
        
        while (true){
            kthPermutation += number.get(k / previousFactorial);
            number.remove(k / previousFactorial);
            n--;
            
            if (n == 0)
                break;
            
            k = k % previousFactorial;
            previousFactorial = previousFactorial / n;
        }
        return kthPermutation;
    }
    
    
    // Brute Force Approach: Generate All Permutations **********************************************
    public String getPermutation_BruteForce(int n, int k) {
        ArrayList<String> allPermutation = new ArrayList<>();
        boolean[] visited = new boolean[n + 1];
        
        getAllPermutation(n, 0, "", visited, allPermutation);
        return allPermutation.get(k - 1);
    }

    public void getAllPermutation(int n, int elementPicked, String permutation, boolean[] visited, ArrayList<String> answer){
        if (elementPicked == n){
            answer.add(permutation);
            return;
        }

        for (int i = 1; i <= n; i++){
            if (!visited[i]){
                visited[i] = true;
                getAllPermutation(n, elementPicked + 1,  permutation + i, visited, answer);
                visited[i] = false;
            }
        }
    }
}