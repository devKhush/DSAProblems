class Solution {
    public int strStr(String text, String pattern) {
        // return text.indexOf(pattern);
        
        int n = text.length();
        int m = pattern.length();
        
        int[] lps = get_LPS_Array(pattern, m);
        
        int i = 0, j = 0;
        
        while (i < n){
            if (text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            else{
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
            if (j == m)
                return i - m;
        }
        return -1;
    }
    
    public int[] get_LPS_Array(String pattern, int m){
        int[] LPS_Array = new int[m];
        int prev_LPS_Length = 0, i = 1;
        
        while (i < m){
            if (pattern.charAt(i) == pattern.charAt(prev_LPS_Length)){
                prev_LPS_Length++;
                LPS_Array[i] = prev_LPS_Length;
                i++;
            }
            else{
                if (prev_LPS_Length == 0){
                    LPS_Array[i] = 0;
                    i++;
                }
                else{
                    prev_LPS_Length = LPS_Array[prev_LPS_Length - 1];
                }
            }
        }
        return LPS_Array;
    }
}