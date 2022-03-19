package Stacks.DecodeString;
import java.util.Stack;

// https://leetcode.com/problems/decode-string/submissions/
// https://www.youtube.com/watch?v=0iQqj5egK9k

class Solution {
    public String decodeString(String s) {
        Stack<String> results = new Stack<>();
        Stack<Integer> counts = new Stack<>();
        
        String result = "";
        int index = 0;
        
        while(index < s.length()){
            if (Character.isDigit(s.charAt(index))){
                int count = 0;
                while(Character.isDigit(s.charAt(index))){
                    count = count*10 + (s.charAt(index) - '0');
                    index++;
                }
                counts.push(count);
            }
            else if (s.charAt(index)=='['){
                results.push(result);
                result = "";
                index++;
            }
            else if (s.charAt(index)==']'){
                StringBuilder previousResult = new StringBuilder(results.pop());
                int count = counts.pop();

                for (int i=0; i<count; i++)
                    previousResult.append(result);
                result = previousResult.toString();
                index++;
            }
            else{           // will be a character
                result += s.charAt(index);
                index++;
            }
        }
        
        return result;
    }
}