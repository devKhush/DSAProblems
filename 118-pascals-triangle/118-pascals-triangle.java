class Solution {
    public List<List<Integer>> generate(int n) {
        List<List<Integer>> allPascals = new ArrayList<>();
        
        for (int i = 0; i < n; i++){
            ArrayList<Integer> currentPascal = new ArrayList<>();
            allPascals.add(currentPascal);
            
            currentPascal.add(1);
            
            for (int j = 1; j <= i; j++){
                int prevPascal1 = allPascals.get(i-1).get(j-1);
                int prevPascal2 = allPascals.get(i-1).size() == j ?  0 : allPascals.get(i-1).get(j);
                
                currentPascal.add(prevPascal1 + prevPascal2);
            }
        }
        
        return allPascals;
        
    }
}