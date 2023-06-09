package Strings.CountAndSay;

public class CountSay_Tabulation {
    /********************************** Efficient Solution 1 *****************************************
     * This Solution is similar to the Tabulation Version as in DP
     * Basically from "Bottom to Up Solution"

     * Time Complexity: O(n)
        * There will be 'n' states/sub-problems in the solution
     * Space Complexity: O(1)
        * We ar using Constant Space to solve
     */
    public String countAndSay(int n) {
        // Answer of Sub-Problem (Base case)
        String subProblemSolution = "1";

        // Go from "Bottom to Up" as in DP (use the answer of previous Sub-Problem to find answer of next Sub-Problem)
        for (int i = 2; i <= n; i++)
            subProblemSolution = say(subProblemSolution);

        return subProblemSolution;
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
