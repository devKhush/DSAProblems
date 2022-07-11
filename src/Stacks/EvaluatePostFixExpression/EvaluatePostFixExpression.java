package Stacks.EvaluatePostFixExpression;
import java.util.Stack;

// https://www.geeksforgeeks.org/stack-set-4-evaluation-postfix-expression/

public class EvaluatePostFixExpression {
    // ******************************* EFFICIENT STACK SOLUTION ***************************************
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a + b);
                }
                case "-" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b - a);
                }
                case "*" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(a * b);
                }
                case "/" -> {
                    int a = stack.pop();
                    int b = stack.pop();
                    stack.push(b / a);
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
