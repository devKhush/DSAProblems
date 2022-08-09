class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        
        for (int i = 1; i <= n/2; i++){
            if (n % i == 0){
                String subString = s.substring(0, i);
                int repeatTimes = n / i;
                
                StringBuilder sb = new StringBuilder();
                
                for (int j = 0; j < repeatTimes; j++)
                    sb.append(subString);
                
                if (equals(s, sb.toString()))
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