package Strings.ReverseWordsInGivenString;
import java.util.Stack;

// https://takeuforward.org/data-structure/reverse-words-in-a-string/

public class ReverseWords {
    /******************************** Approach 1 : Using Stack *********************************
     * Idea is to use a Stack & traverse the String from the end.

     * Time Complexity: O(2 * n) = O(n)
        * We are doing two traversal for characters stack push() and pop()
     * Space Complexity: O(n)
        * Due to stack used
     */
    public String reverseWords_Stack(String s) {
        s = " " + s;
        int n = s.length();

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        // Traverse Backwards
        for (int i = n - 1; i >= 0; i--){
            char ch = s.charAt(i);

            // If current char is not " ", keep on adding to the stack
            if (ch != ' ')
                stack.push(ch);
            else{
                // Add a space between the words if stack is not empty
                if (!stack.isEmpty())
                    stack.push(' ');

                // Add all the characters from stack into the StringBuilder() if current character is " "
                while (!stack.isEmpty())
                    sb.append(stack.pop());
            }
        }
        return sb.toString().trim();
    }


    /******************************** Efficient Solution: Without Extra Space *****************************
     * Two Pointer Approach

     * Time Complexity: O(2*n)
        * At most traversal by two pointers
     * Space Complexity: O(1)
     */
    public String reverseWords_(String s) {
        StringBuilder sb = new StringBuilder();

        // Initialize a pointer to the end of the string and run a while loop until the pointer
        // reaches the start of the string
        int i = s.length() - 1;

        while (i >= 0){
            // Skip multiple spaces by decreasing the pointer
            if (s.charAt(i) == ' '){
                i--;
            }
            // If we found a character which is not " ", we traverse back via pointer until string
            // contains no more characters OR we found a " ".
            // We add the substring() into our answer
            else{
                // Add a space between the words
                if (!sb.isEmpty())
                    sb.append(" ");

                int j = i;
                while (j >= 0  &&  s.charAt(j) != ' ')
                    j--;

                // Currently, 'j' is at the index having character " "
                sb.append(s.substring(j + 1, i + 1));
                i = j;
            }
        }
        return sb.toString();
    }


    /*********************************** Another Same Compact Solution *******************************
     * Two Pointer Approach
     * Time Complexity: O(2*n)
        * At most traversal by two pointers
     * Space Complexity: O(1)
     */
    public String reverseWords(String s) {
        int n = s.length();
        int j = n - 1, i = n - 1;
        StringBuilder sb = new StringBuilder();

        while (i >= 0){
            while (i >= 0  && s.charAt(i) != ' ')
                i--;
            if (i < j){
                sb.append(s.substring(i + 1, j + 1));
                sb.append(" ");
            }
            i--;
            j = i;
        }
        return sb.substring(0, sb.length() - 1);
    }
}
