package Strings.LongestCommonPrefix;

/**
 * Divide and Conquer Approach

 * Time Complexity: O(N*M)
    * In the worst case, we are making the N comparison, and in each comparison, we are traversing
        through the length of the string that takes O(M) time. Hence, the overall time complexity is O(N*M).

 * Space Complexity: O(M * log(N))
    * There is log(N) recursive calls, and in each recursive call, we need O(M) space to store the
        longest prefix string. Hence, the overall space complexity is O(M*log(N)).

 Where N is the number of strings in the array and M is the length of the shortest string present in
 the array.
*/

public class LongestCommonPrefix_DnC {
    /****************************** Another Solution using Divide and Conquer ****************************
     * Time Complexity: O(x * log(n))
     * Space Complexity: O(n)
     */
    public String longestCommonPrefix(String[] strs){
        return dnc(0, strs.length - 1, strs);
    }

    public String dnc(int low, int high, String[] strs){
        if (low == high)
            return strs[low];
        int mid = low + (high - low)/2;
        return commonPrefix(dnc(low, mid, strs), dnc(mid + 1, high, strs));
    }

    public String commonPrefix(String str1, String str2) {
        int n = Math.min(str1.length(), str2.length());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++){
            if (str1.charAt(i) == str2.charAt(i))
                sb.append(str1.charAt(i));
            else break;
        }
        return sb.toString();
    }

}
