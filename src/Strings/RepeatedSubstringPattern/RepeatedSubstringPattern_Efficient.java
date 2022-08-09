package Strings.RepeatedSubstringPattern;

// PRE-REQUISITE: "KMP-ALGORITHM" and "LPS ARRAY"
// Great Intuition for use of LPS Array:
// https://youtu.be/lumwBLJOQpk
// https://www.geeksforgeeks.org/find-given-string-can-represented-substring-iterating-substring-n-times/

public class RepeatedSubstringPattern_Efficient {
    /********************************* Efficient Solution: using LPS Array *******************************
     * Intuition:
        * To check if a string consists of repeated substrings, we have to check if "n", length(string),
            is a multiple of "n - k", where "k" is the length of the longest proper prefix which is also
            suffix and is different from 0. Think...

     * Say, length(input_string) = n
     * Say, length(substring that can be repeated to form input string) = k
     * Clearly, k <= n
     * The "n / k" will be no. of times substring has to be repeated to produce input string.
     * Also, n % k == 0. The "n" must be a multiple of "k", as no. of repetitions has to be an
        integer number.

     * Time Complexity: O(n)
        * LPS Array required O(n) time to be build.
     * Space Complexity: O(n)
        * O(n) Space is required for LPS Array creation.
     */
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();

        // Compute LPS Array of String
        int[] LPS = getLPSArray(s, n);

        // If last element in LPS array is 0, it means length of the "longest prefix which is also the
        // suffix" is 0. This further implies prefix and suffix are not same, hence the repetition
        // is not possible
        if (LPS[n - 1] == 0)
            return false;

        // Get the last value of LPS array. It denotes length of the "longest suffix which is also the
        // prefix" in the string "s"
        int longestSuffixLength = LPS[n - 1];

        // Say "len" = length of the Longest Suffix at the end which is also the prefix
        // If there exist a suffix which is also prefix AND the length of the remaining
        // substring divides total length (it will be the one that can replicate to form entire string).
        // Then str[0, n-len-1] is the substring that repeats n/(n-len) times to form input string
        return n % (n - longestSuffixLength) == 0;
    }


    // Compute the LPS Array used in KMP Algorithm
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


    /******************************* Another Solution ***************************************
     * But this solution is slower than previous one.
     * Intuition: A repeated substring is non-trivial rotation of itself.
     * For Intuition & proof See video.
     * This solution is Not recommended though, as better & easier solution using LPS array exists

     * Time Complexity: O(2 * n) + O(2*n + n) = O(5 * n) = O(n)
        * O(2 * n) for concatenated String
        * O(2*n + n)  for searching the pattern in text using KMP Algorithm (it used O(m + n) time)
     * Space Complexity: O(2 * n) + O(2 * n)  =  O(4 * n) = o(n)
        * Length of Concatenated String is double of that of input string (2*n).
        * LPS Array for Concatenated String will take O(2n) space
     */
    public boolean repeatedSubstringPattern_V2(String s) {
        int n = s.length();

        // Concatenated String used for checking repeated string
        String concatenatedString = (s + s).substring(1, 2*n - 1);

        // KMP Algorithm to check whether "s" is present in Concatenated String
        return containsString_KMP(concatenatedString, s);
    }

    // Same as searching a pattern in a text using KMP Algorithm
    private boolean containsString_KMP(String text, String pattern){
        int n = text.length();
        int m = pattern.length();

        int[] lps = getLPSArray(pattern, m);
        int i = 0, j = 0;

        while (i < n){
            if (text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            else if (j == 0)
                i++;
            else
                j = lps[j - 1];

            if (j == m)
                return true;
        }
        return false;
    }
}