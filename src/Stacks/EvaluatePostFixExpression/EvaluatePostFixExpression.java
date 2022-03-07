package Stacks.EvaluatePostFixExpression;

import java.util.Stack;


public class EvaluatePostFixExpression {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int a, b;
        
        for (int i=0; i<tokens.length; i++){
            switch(tokens[i]){
                case "+":
                    b = stack.pop(); a = stack.pop();
                    stack.push(a+b);
                    break;
                case "-":
                    b = stack.pop(); a = stack.pop();
                    stack.push(a-b);
                    break;
                case "*":
                    b = stack.pop(); a = stack.pop();
                    stack.push(a*b);
                    break;
                case "/":
                    b = stack.pop(); a = stack.pop();
                    stack.push(a/b);
                    break;
                default:
                    stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.peek();
    }
}
