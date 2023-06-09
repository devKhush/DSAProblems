package Strings.LongestPalindromicSubstring;

// https://leetcode.com/problems/longest-palindromic-substring/solution/
// https://youtu.be/y2BD4MJqV20

public class LongestPalindromeSubstring_Best {
    /*
     ********************************* Approach 3: Expand Around Center ******************************
     * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded
     * from its center, and there are only 2n−1 such centers.
     * You might be asking why there are 2n-1 but not n centers?
     *  The reason is the center of a palindrome can be in between two letters.
     * Such palindromes have even numbers of letters (such as "abba") and its center are between the two 'b's.
     *
     * Since expanding a palindrome around its center could take O(n) time, the overall complexity is O(n^2)
     * If we have used string.charAt(i), TC would become O(n^3)
     * Time complexity : O(n^2)
     * Space complexity : O(1)      If we ignore char array & output string
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        int palindromeLength = 0;
        int start = 0;

        for (int i = 0; i < n; i++) {
            // checks for odd length palindrome substrings
            int palinLen1 = expandPalindrome(i, i, s, n);

            // checks for even length palindrome substrings
            int palinLen2 = expandPalindrome(i, i + 1, s, n);

            if (palinLen1 > palindromeLength) {
                palindromeLength = palinLen1;
                start = i - (palinLen1 / 2);
            }
            if (palinLen2 > palindromeLength) {
                palindromeLength = palinLen2;
                start = i - (palinLen2 / 2 - 1);
            }
        }
        return s.substring(start, start + palindromeLength);
    }

    private int expandPalindrome(int low, int high, String s, int n) {
        // Expand the current palindrome, till its characters at left & right pointers are equal
        while (low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
            low--;
            high++;
        }
        return high - low - 1;
    }
}
