package Strings.MinimumAdditionsToMakeValidString;

// https://leetcode.com/problems/minimum-additions-to-make-valid-string/

public class MinimumAdditionsToMakeValidString {
    /************************************* Solution *****************************************
     * TC -> O(n)
     * SC -> O(1)
     */
    public int addMinimum(String word) {
        int n = word.length();
        int i = 0;
        int charsRequired = 0;

        while (i < n) {
            char ch = word.charAt(i);
            if (ch == 'a') {
                if (i + 1 < n && word.charAt(i + 1) == 'b')
                    i++;
                else
                    charsRequired++;
                if (i + 1 < n && word.charAt(i + 1) == 'c')
                    i++;
                else
                    charsRequired++;
            }
            else if (ch == 'b') {
                charsRequired++;
                if (i + 1 < n && word.charAt(i + 1) == 'c')
                    i++;
                else
                    charsRequired++;
            }
            else if (ch == 'c') {
                charsRequired += 2;
            }
            i++;
        }
        return charsRequired;
    }
}
