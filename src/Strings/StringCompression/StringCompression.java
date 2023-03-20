package Strings.StringCompression;

// https://leetcode.com/problems/string-compression/description/

public class StringCompression {
    /******************************* Two Pointer Solution ************************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int compress(char[] chars) {
        int i = 0;
        int idx = 0;
        int n = chars.length;

        while (i < n){
            // Two pointer movement
            char ch = chars[i];
            int j = i;
            int length = 0;
            while (j < n  &&  chars[i] == chars[j]){
                length++;
                j++;
            }
            i = j;


            // Write back the answer
            chars[idx++] = ch;
            if (length > 1){
                String len = Integer.toString(length);
                for (int a = 0; a < len.length(); a++)
                    chars[idx++] = len.charAt(a);
            }
        }
        return idx;
    }
}
