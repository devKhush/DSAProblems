package Stacks.ValidateStackSequences;
import java.util.Stack;

public class ValidateStackSequences {
    /*********************************** Stack Solution **********************************************
     * Time Complexity: O(n) + O(m)
        * n and m are the size of 'pushed' and 'popped' array
     * Space Complexity: O(n)
        * n is the size of 'pushed' array
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;

        for (int i = 0; i < pushed.length; i++){
            stack.push(pushed[i]);
            while (!stack.isEmpty()  &&  stack.peek() == popped[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
