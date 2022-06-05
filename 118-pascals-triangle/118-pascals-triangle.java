class Solution {
    public List<List<Integer>> generate(int n) {
        List<List<Integer>> allPascals = new ArrayList<>();
        
        for (int i = 0; i < n; i++){
            ArrayList<Integer> currentPascal = new ArrayList<>();
            
            for (int j = 0; j <= i; j++){
                if (j == 0)
                    currentPascal.add(1);
                
                else if (j == i)
                    currentPascal.add(1);
                
                else{
                    int prevPascal1 = allPascals.get(i-1).get(j-1);
                    int prevPascal2 = allPascals.get(i-1).get(j);
                    currentPascal.add(prevPascal1 + prevPascal2);
                }
            }
            
            allPascals.add(currentPascal);
        }
        
        return allPascals;
    }
}