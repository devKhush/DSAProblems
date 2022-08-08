class Solution {
    public int repeatedStringMatch(String a, String b) {
        int n = a.length();
        int m = b.length();

        StringBuilder repeatedString = new StringBuilder();
        int repetitionCount = 0;
        int repeatedStrLength = 0;

        while (repeatedStrLength < m){
            repeatedString.append(a);
            repeatedStrLength += n;
            repetitionCount++;
        }
        
        /*
        if (repeatedString.toString().contains(b)) 
            return repetitionCount;
         */
        if (containsPattern_KMP(repeatedString.toString(), b))
            return repetitionCount;

        repeatedString.append(a);
        repetitionCount++;

        /*
        if (repeatedString.toString().contains(b)) 
            return repetitionCount;
         */
        if (containsPattern_KMP(repeatedString.toString(), b))
            return repetitionCount;

        return -1;
    }

    
    public boolean containsPattern_KMP(String text, String pattern){
        int n = text.length();
        int m = pattern.length();

        int[] lpsArray = getLPSArray(pattern, m);

        int i = 0, j = 0;

        while (i < n){
            if (text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            else{
                if (j != 0)
                    j = lpsArray[j - 1];
                else
                    i++;
            }
            if (j == m)
                return true;
        }
        return false;
    }

    public int[] getLPSArray(String pattern, int m){
        int[] LPS = new int[m];

        int prev_LPS_Length = 0, i = 1;

        while (i < m){
            if (pattern.charAt(i) == pattern.charAt(prev_LPS_Length)){
                prev_LPS_Length++;
                LPS[i++] = prev_LPS_Length;
            }
            else{
                if (prev_LPS_Length == 0){
                    LPS[i] = 0;
                    i++;
                }
                else
                    prev_LPS_Length = LPS[prev_LPS_Length - 1];
            }
        }
        return LPS;
    }
}