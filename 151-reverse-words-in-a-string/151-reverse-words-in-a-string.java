class Solution {
    public String reverseWords(String s) {
        s = s.trim() + " ";
        int n = s.length();

        StringBuilder answer = null;
        StringBuilder currWord = new StringBuilder();

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);

            if (ch != ' ')
                currWord.append(ch);

            else{
                if (currWord.toString().equals(""))
                    continue;
                if (answer != null)
                    answer.insert(0, currWord + " ");
                else
                    answer = new StringBuilder(currWord.toString());

                currWord = new StringBuilder();
            }
        }
        return answer.toString();
    }
    
    
    public String reverseWords_Stack(String s) {
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