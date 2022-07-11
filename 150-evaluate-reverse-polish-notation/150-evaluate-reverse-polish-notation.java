class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < tokens.length; i++){
            switch(tokens[i]){
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "-" -> {
                    int a = stack.pop();          
                    int b = stack.pop();    
                    stack.push(b - a);
                }
                case "*" -> {
                    int a = stack.pop();          
                    int b = stack.pop();    
                    stack.push(b * a);
                }
                case "/" -> {
                    int a = stack.pop();          
                    int b = stack.pop();    
                    stack.push(b / a);
                }
                default -> stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }
}