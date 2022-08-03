class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        s = " " + s;
        int n = s.length();
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--){
            char ch = s.charAt(i);
            
            if (ch != ' ')
                stack.push(ch);
            else{
                boolean stackWasNotEmpty = !stack.isEmpty();
                
                while (!stack.isEmpty())
                    sb.append(stack.pop());
                
                if (stackWasNotEmpty)
                    sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}