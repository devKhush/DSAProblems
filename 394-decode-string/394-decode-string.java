class Solution {
    public String decodeString(String s) {
        Stack<String> results = new Stack<>();
        Stack<Integer> counts = new Stack<>();
        
        String result = "";
        int index = 0;
        
        while(index < s.length()){
            if (Character.isDigit(s.charAt(index))){
                int count = 0;
                while(Character.isDigit(s.charAt(index))){
                    count = count*10 + (s.charAt(index) - '0');
                    index++;
                }
                counts.push(count);
            }
            else if (s.charAt(index)=='['){
                results.push(result);
                result = "";
                index++;
            }
            else if (s.charAt(index)==']'){
                String previousResult = results.pop();
                int count = counts.pop();

                for (int i=0; i<count; i++)
                    previousResult += result;
                result = new String(previousResult);
                index++;
            }
            else{
                result += s.charAt(index);
                index++;
            }
        }
        
        return result;
    }
}