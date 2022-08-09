class Solution {
    public boolean repeatedSubstringPattern(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        
        for (int i = n/2; i >= 1; i--){
            if (n % i == 0  &&  canRepeatToFormWholeString(s, i))
                return true;
        }
        return false;
    }
    
    private boolean canRepeatToFormWholeString(char[] s, int start){
        int n = s.length;
        int j = 0;
        
        for (int i = start; i < n; i++){
            if (s[i] != s[j])
                return false;
            
            j++;
            if (j == start)
                j = 0;
        }
        return true;
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