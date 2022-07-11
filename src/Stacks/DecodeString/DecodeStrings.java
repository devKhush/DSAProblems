package Stacks.DecodeString;
import java.util.Stack;

// https://leetcode.com/problems/decode-string/submissions/
// https://www.youtube.com/watch?v=0iQqj5egK9k

public class DecodeStrings {
    // *************************************** Solution - 1 ***************************************
    // Time Complexity: O(n)
    // Space Complexity: O(2*n) = O(n)
    public String decodeString(String s) {
        Stack<String> results = new Stack<>();
        Stack<Integer> counts = new Stack<>();

        String decodedString = "";
        int n = s.length();
        int i = 0;

        while (i < n){
            if (Character.isDigit(s.charAt(i))){
                int number = 0;
                while (Character.isDigit(s.charAt(i))){
                    number = number * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                counts.push(number);
            }
            else if (s.charAt(i) == '['){
                results.push(decodedString);
                decodedString = "";
            }
            else if (s.charAt(i) == ']'){
                String previousResult = results.pop();
                StringBuilder sb = new StringBuilder(previousResult);
                int repeatCount = counts.pop();

                while (repeatCount-- > 0)
                    sb.append(decodedString);

                decodedString = sb.toString();
            }
            else
                decodedString += s.charAt(i);
            i++;
        }
        return decodedString;
    }


    // *************************************** Solution - 2 ***************************************
    // Time Complexity: O(n)
    // Space Complexity: O(2*n) = O(n)
    public String decodeString_CompactCode_Efficient(String s) {
        int n = s.length();

        Stack<String> results = new Stack<>();
        Stack<Integer> counts = new Stack<>();
        StringBuilder decodedString = new StringBuilder("");
        int number = 0;

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);

            if (ch >= '0' && ch <= '9')
                number = number * 10  + (ch - '0');

            else if (ch == '['){
                results.push(decodedString.toString());
                counts.push(number);
                number = 0;
                decodedString = new StringBuilder();
            }
            else if (ch == ']'){
                int repeat = counts.pop();
                String previousResult = results.pop();

                String currentResult = decodedString.toString();
                decodedString = new StringBuilder(previousResult);
                while (repeat-- > 0)
                    decodedString.append(currentResult);
            }
            else
                decodedString.append(ch);
        }
        return decodedString.toString();
    }
}