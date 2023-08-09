package Recursions_And_BackTracking.LetterCombinationsOfPhoneNumber;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

public class LetterCombinationsOfPhoneNumber {
    /*************************************** Recursion *********************************************
     * Time Complexity: O(exponential)
     * Space Complexity: O(n)
        * Ignoring output space
     */
    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        HashMap<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        List<String> combinations = new ArrayList<>();
        if (n != 0){
            f(0, digits, "", combinations, map);
        }
        return combinations;
    }

    private void f(int i, String digits, String s, List<String> combinations, HashMap<Integer, String> map){
        if (digits.length() == s.length()){
            combinations.add(s);
            return;
        }
        for (int j = 0; j < map.get(digits.charAt(i)-'0').length(); j++){
            f(i + 1, digits, s + map.get(digits.charAt(i)-'0').charAt(j), combinations, map);
        }
    }
}
