package Strings.RomanNumber.RomanNumberToInteger;
import java.util.HashMap;

public class RomanNumberToInteger {
    /************************************ Efficient Solution using HashMap *****************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int romanToInt_V1(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int n = s.length();
        int number = 0;
        for (int i = 0; i < n; i++){
            if (i + 1 < n  &&  map.get(s.charAt(i)) < map.get(s.charAt(i + 1)))
                number -= map.get(s.charAt(i));
            else
                number += map.get(s.charAt(i));
        }
        return number;

    }

    /************************************ Efficient Solution using HashMap *****************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int romanToInt_V2(String s) {
        int[] romanMap = new int[26];
        romanMap['I' - 'A'] =  1;
        romanMap['V' - 'A'] =  5;
        romanMap['X' - 'A'] =  10;
        romanMap['L' - 'A'] =  50;
        romanMap['C' - 'A'] =  100;
        romanMap['D' - 'A'] =  500;
        romanMap['M' - 'A'] =  1000;

        int n = s.length();
        int number = 0;
        for (int i = 0; i < n; i++){
            int currRomanNum = romanMap[s.charAt(i) - 'A'];
            if (i + 1 < n  &&  currRomanNum < romanMap[s.charAt(i + 1) - 'A'])
                number -= currRomanNum;
            else
                number += currRomanNum;
        }
        return number;
    }
}
