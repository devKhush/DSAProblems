class Solution {
    
    public boolean wordBreak(String s, List<String> wordDict){
        int n = s.length();
        HashMap<Integer, Boolean> dp = new HashMap<>(); 
        
        return canBreakWord(0, n, s, wordDict, dp);
    }
    public boolean canBreakWord(int index, int n, String s, List<String> dictionary, HashMap<Integer, Boolean> dp){
        if (index == n)
            return true;

        if (dp.containsKey(index))
            return dp.get(index);

        for (int i = index; i < n; i++){
            String subString = s.substring(index, i + 1);

            if (dictionary.contains(subString)  &&  canBreakWord(i + 1, n, s, dictionary, dp)){
                dp.put(index, true);
                return true;
            }
        }
        dp.put(index, false);
        return false;
    }
    
    // Memoization *************************************************************************
    public boolean wordBreak_Memoization(String s, List<String> wordDict){
        int n =s.length();

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        canBreakWord(0, n, s, wordDict, dp);
        return dp[0] == 1;
    }
    public boolean canBreakWord(int index, int n, String s, List<String> dictionary, int[] dp){
        if (index == n)
            return true;

        if (dp[index] != -1)
            return dp[index] == 1 ? true : false;

        for (int i = index; i < n; i++){
            String subString = s.substring(index, i + 1);

            if (dictionary.contains(subString)  &&  canBreakWord(i + 1, n, s, dictionary, dp)){
                    dp[index] = 1;
                    return true;
            }
        }
        dp[index] = 0;
        return false;
    }
    
    
    
    // BACK TRACKING Solution **************************************************************
    public boolean wordBreak_BackTracking(String s, List<String> wordDict) {
        return canBreakWord(0, s, s.length(), new HashSet<>(wordDict));
    }
    private boolean canBreakWord(int index, String s, int n, HashSet<String> wordDict){
        if (index == n)
            return true;

        for (int i = index; i < n; i++){
            if (wordDict.contains(s.substring(index, i + 1))){
                if (canBreakWord(i + 1, s, n, wordDict))
                    return true;
            }
        }
        return false;
    }   
}