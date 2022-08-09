class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] LPS = getLPSArray(s, n);
        
        if (LPS[n - 1] == 0)
            return false;
        
        int longestPrefix = LPS[n - 1];
        
        return longestPrefix % (n - longestPrefix) == 0;
    }
    
    private int[] getLPSArray(String s, int n){
        int[] LPS = new int[n];
        int prev_LPS_Length = 0;
        int i = 1;
        
        while (i < n){
            if (s.charAt(i) == s.charAt(prev_LPS_Length)){
                prev_LPS_Length++;
                LPS[i] = prev_LPS_Length;
                i++;
            }
            else if (prev_LPS_Length == 0){
                LPS[i] = 0;
                i++;
            }
            else
                prev_LPS_Length = LPS[prev_LPS_Length - 1];
        }
        return LPS;
    }
    
    
    // Brute Force: Time Complexity: O(n^2)
    public boolean repeatedSubstringPattern_Brute(String s) {
        int n = s.length();
        
        // for (int i = 1; i <= n/2; i++){
        for (int i = n/2; i >= 1; i--){
            if (n % i == 0){
                String subString = s.substring(0, i);
                int repeatTimes = n / i;
                
                StringBuilder sb = new StringBuilder();
                
                for (int j = 0; j < repeatTimes; j++)
                    sb.append(subString);
                
                if (s.equals(sb.toString()))
                    return true;
            }
        }
        return false;
    }
    
    private boolean equals(String s1, String s2){
        int n = s1.length();
        int i = 0, j = 0;
        
        while (i < n && j < n){
            if (s1.charAt(i) != s2.charAt(j))
                return false;
            
            i++;
            j++;
        }
        return true;
    }
}