package Strings.SubstringWithLargestVariance;

// https://leetcode.com/problems/substring-with-largest-variance/
// https://leetcode.com/problems/substring-with-largest-variance/editorial/

public class SubstringWithLargestVariance {
    /************************************ Kadane Algorithm ***************************************
     * Intuition:
        * Read editorial

     * Time Complexity: O(n * 26 * 26)
     * Space Complexity: O(26) ~ O(1)
     */
    public int largestVariance(String s) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++){
            count[s.charAt(i) - 'a']++;
        }
        int maxVariance = 0;

        for (char a = 'a'; a <= 'z'; a++){
            for (char b = 'a'; b <= 'z'; b++){
                if (a == b || count[a - 'a'] == 0 || count[b - 'a'] == 0)
                    continue;
                int major = 0;
                int minor = 0;
                int leftMinor = count[b - 'a'];

                for (int i = 0; i < n; i++){
                    char ch = s.charAt(i);
                    if (ch == a){
                        major++;
                    }
                    if (ch == b){
                        minor++;
                        leftMinor--;
                    }
                    if (minor > 0)
                        maxVariance = Math.max(maxVariance, major - minor);

                    if (minor > major && leftMinor > 0){
                        major = minor = 0;
                    }
                }
            }
        }
        return maxVariance;
    }
}
