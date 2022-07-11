class Solution {
    public String decodeString(String s) {
        int n = s.length();
        
        Stack<String> results = new Stack<>();
        Stack<Integer> counts = new Stack<>();
        StringBuilder decodedString = new StringBuilder("");
        int number = 0;
        
        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);
            
            if (ch >= '0' && ch <= '9')
                number = number * 10  + (ch - '0');
            
            else if (ch == '['){
                results.push(decodedString.toString());
                counts.push(number);
                number = 0;
                decodedString = new StringBuilder();
            }
            else if (ch == ']'){
                int repeat = counts.pop();
                String previousResult = results.pop();
                
                String currentResult = decodedString.toString();
                decodedString = new StringBuilder(previousResult);
                while (repeat-- > 0)
                    decodedString.append(currentResult);
            }
            else
                decodedString.append(ch);
        }
        
        return decodedString.toString();
    }
}