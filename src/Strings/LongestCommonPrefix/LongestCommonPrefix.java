package Strings.LongestCommonPrefix;

public class LongestCommonPrefix {
    /******************************* Efficient Solution 1 ********************************************
     * n -> Size of String array
     * x -> average length of each string

     * Time Complexity: O(n * x)
     * Space Complexity: O(1)
        * Neglecting output Space
     */
    public String longestCommonPrefix_V1(String[] strs) {
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++){
            int n = Math.min(prefix.length(), strs[i].length());
            int j = 0;
            while (j < n){
                if (prefix.charAt(j) != strs[i].charAt(j))
                    break;
                j++;
            }
            prefix = prefix.substring(0, j);
        }
        return prefix;
    }


    /******************************* Efficient Solution 2 ********************************************
     * n -> Size of String array
     * x -> average length of each string

     * Time Complexity: O(n * x)
     * Space Complexity: O(1)
        * Neglecting output Space
     */
    public String longestCommonPrefix_V2(String[] strs){
        int min_length = Integer.MAX_VALUE;
        for (String str : strs) {
            min_length = Math.min(min_length, str.length());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < min_length; i++){
            char ch = strs[0].charAt(i);

            for (int j = 0; j < strs.length; j++){
                if (strs[j].charAt(i) != ch)
                    return sb.toString();
            }
            sb.append(ch);
        }
        return "";
    }
}
