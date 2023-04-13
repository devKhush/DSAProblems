package Stacks.RemovingStarsFromString;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class RemovingStarsFromString {
    /***************************** Solution 1 using Stack ***************************************
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public String removeStars_stack(String s) {
        Stack<Character> stack = new Stack<>();
        int n = s.length();

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if (ch == '*')
                stack.pop();
            else
                stack.push(ch);
        }
        char[] arr = new char[stack.size()];
        for (int i = arr.length - 1; i >= 0; i--)
            arr[i] = stack.pop();
        return new String(arr);
    }


    /***************************** Solution 2 using Deque ***************************************
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public String removeStars(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int n = s.length();

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);
            if (ch == '*')
                stack.removeLast();
            else
                stack.addLast(ch);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.removeFirst());
        return sb.toString();
    }
}

