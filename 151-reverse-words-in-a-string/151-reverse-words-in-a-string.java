class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;

        while (i >= 0){
            if (s.charAt(i) != ' '){
                if (!sb.isEmpty())
                    sb.append(" ");

                int j = i;

                while (j >= 0  &&  s.charAt(j) != ' ')
                    j--;

                sb.append(s.substring(j + 1, i + 1));
                i = j;
            }
            else
                i--;
        }
        return sb.toString();
    }
    

    public String reverseWords_Efficient(String s) {
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
                    answer = new StringBuilder(currWord.toString( ));

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