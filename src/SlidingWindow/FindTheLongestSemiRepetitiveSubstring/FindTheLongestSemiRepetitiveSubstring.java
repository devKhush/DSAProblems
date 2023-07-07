package SlidingWindow.FindTheLongestSemiRepetitiveSubstring;

public class FindTheLongestSemiRepetitiveSubstring {
    /**************************************** Brute Force *****************************************
     * Try out all substrings one by one
     * Time Complexity: O(n*n)
     * Space Complexity: O(1)
     */
    public int longestSemiRepetitiveSubstring_brute(String s) {
        int n = s.length();
        int maxLength = 0;

        for (int i = 0; i < n; i++){
            boolean samePairFound = false;
            for (int j = i; j < n; j++){
                if (j > i  &&  s.charAt(j - 1) == s.charAt(j)){
                    if (samePairFound)
                        break;
                    samePairFound = true;
                }
                maxLength = Math.max(maxLength, j - i + 1);
            }
        }
        return maxLength;
    }


    /*************************************** Sliding Window ****************************************
     * Intuition: Same Sliding Window Pattern
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int countSame = 0;
        int left = 0;

        for (int right = 0; right < n; right++){
            if (right > 0  &&  s.charAt(right - 1) == s.charAt(right))
                countSame++;

            while (countSame > 1){
                if (s.charAt(left) == s.charAt(left + 1))
                    countSame--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }
}
