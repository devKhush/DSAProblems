package Strings.CountAndSay;

public class CountAndSay_Memoization {
    /********************************** Efficient Solution 1 *****************************************
     * This Solution is similar to the Memoization Version as in DP
     * Basically from "Top to down Solution"
     *
     * Time Complexity: O(n)
        * There will be 'n' states/sub-problems in the solution
     * Space Complexity: O(n)
        * Due to 'n' Recursion calls
     */
    public String countAndSay(int n) {
        if (n == 1)
            return "1";
        String prevSay = countAndSay(n - 1);
        return say(prevSay);
    }

    private String say(String number){
        StringBuilder sb = new StringBuilder();
        int n = number.length();
        int i = 0;
        while (i < n){
            int count = 1;
            while (i < n-1  &&  number.charAt(i) == number.charAt(i + 1)){
                i++;
                count++;
            }
            sb.append(count);
            sb.append(number.charAt(i));
            i++;
        }
        return sb.toString();
    }
}