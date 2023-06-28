package Recursions_And_BackTracking.GenerateParentheses;
import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/generate-parentheses/
// https://leetcode.com/problems/generate-parentheses/editorial/

public class GenerateParentheses {

    /******************************************* Solution 1 *******************************************
     * Intuition: Simple Recursion thinking
     * Time Complexity: O(2^n) ~ exponential
        * two choices at every index values
     * Space Complexity: O(n)
        * Recursion stack space
     * **/
    int i = 0;
    public List<String> generateParenthesis_(int n) {
        ArrayList<String> allParenthesis = new ArrayList<>();
        String[] list = new String[2*n];
        f(n, n, allParenthesis, list);
        return allParenthesis;
    }
    // frontLeft denotes no. of available "(" character
    // backLeft denotes no. of available ")" character
    private void f(int frontLeft, int backLeft, ArrayList<String> allParenthesis, String[] list){
        if (frontLeft == 0 && backLeft == 0){
            allParenthesis.add(String.join("", list));
            return;
        }
        if (frontLeft > 0){
            list[i++] = "(";
            f(frontLeft - 1, backLeft, allParenthesis, list);
            i--;
        }
        if (frontLeft < backLeft){
            list[i++] = ")";
            f(frontLeft, backLeft - 1, allParenthesis, list);
            i--;
        }
    }

    /******************************************* Solution 2 ******************************************
     * Intuition: Simple Recursion thinking
     * Time Complexity: O(2^n)
        * two choices at every index values
     * Space Complexity: O(n)
        * Recursion stack space
     **/
    public List<String> generateParenthesis(int n) {
        ArrayList<String> allParenthesis = new ArrayList<>();
        f(n, n, "", allParenthesis);
        return allParenthesis;
    }

    // frontLeft denotes no. of available "(" character
    // backLeft denotes no. of available ")" character
    private void f(int frontLeft, int backLeft,String str, ArrayList<String> allParenthesis){
        if (frontLeft == 0 && backLeft == 0){
            allParenthesis.add(str);
            return;
        }
        if (frontLeft > 0){
            f(frontLeft - 1, backLeft, str + "(", allParenthesis);
        }
        if (frontLeft < backLeft){
            f(frontLeft, backLeft - 1, str + ")", allParenthesis);
        }
    }
}
