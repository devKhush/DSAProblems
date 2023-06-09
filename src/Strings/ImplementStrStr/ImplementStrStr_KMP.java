package Strings.ImplementStrStr;

// PRE-REQUISITE: "KMP Algorithm : Pattern Matching"
// MUST Watch Great Video with Deep Intuition: https://youtu.be/BXCEFAzhxGY
// LPS Array: https://youtu.be/JoF0Z7nVSrA
// https://youtu.be/V5-7GzOfADQ
// https://youtu.be/ziteu2FpYsA

// Great Article: https://www.scaler.com/topics/data-structures/kmp-algorithm/
// http://www.btechsmartclass.com/data_structures/knuth-morris-pratt-algorithm.html
// https://www.geeksforgeeks.org/naive-algorithm-for-pattern-searching/
// https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/

public class ImplementStrStr_KMP {
    /******************************* KMP Pattern Matching Algorithm **********************************
     * For more details see KMP Algorithm
     * Time Complexity: O(m + n)
        * LPS array takes O(m) time
        * Searching takes O(n) time
     * Space Complexity: O(m)
        * LPS array for pattern
     */
    // KMP Algorithm
    public int strStr(String text, String pattern) {
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
                if (j == 0)
                    i++;
                else
                    j = lps[j - 1];
            }
            if (j == m)         // return the starting index of pattern
                return i - m;
        }
        return -1;
    }

    // Find the LPS Array (The Longest Prefix Suffix) of the String
    // We use the same concept that we used in string matching in text and pattern to compute the lps array
    public int[] get_LPS_Array(String pattern, int m){
        int[] lps = new int[m];
        int length = 0, i = 1;

        while (i < m){
            if (pattern.charAt(i) == pattern.charAt(length)){
                length++;
                lps[i] = length;
                i++;
            }
            else{
                if (length == 0)
                    i++;
                else
                    length = lps[length - 1];
            }
        }
        return lps;
    }
    /**
    * Notice carefully code to find the LPS array and string matching are exactly same.
     * Difference is that we are doing computation on two strings (while matching) and one string (lps finding).
     */
}
