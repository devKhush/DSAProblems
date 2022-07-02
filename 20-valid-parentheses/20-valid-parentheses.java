class Solution {
    public boolean isValid(String s) {
       Stack<Character> stack = new Stack<>();
        int n = s.length();

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);

            switch (ch){
                case ')':
                    if (!stack.isEmpty() && stack.peek() == '(')
                        stack.pop();
                    else return false;
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '{')
                        stack.pop();
                    else return false;
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[')
                        stack.pop();
                    else return false;
                    break;
                default:
                    stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}


