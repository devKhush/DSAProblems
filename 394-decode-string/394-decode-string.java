class Solution {
    public String decodeString(String s) {
        Stack<String> results = new Stack<>();
        Stack<Integer> counts = new Stack<>();
        
        String decodedString = "";
        int n = s.length();
        int i = 0;
        
        while (i < n){
            if (Character.isDigit(s.charAt(i))){
                int number = 0;
                
                while (Character.isDigit(s.charAt(i))){
                    number = number * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                counts.push(number);
            }
            
            else if (s.charAt(i) == '['){
                results.push(decodedString);
                decodedString = "";
            }
            
            else if (s.charAt(i) == ']'){
                StringBuilder sb = new StringBuilder(results.pop());
                int repeatCount = counts.pop();
                
                while (repeatCount-- > 0)
                    sb.append(decodedString);
                
                decodedString = sb.toString();
            }
            
            else
                decodedString += s.charAt(i);
            i++;
        }
        return decodedString;
    }
}