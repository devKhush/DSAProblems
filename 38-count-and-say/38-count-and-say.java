class Solution {
    public String countAndSay(int n) {
        if (n == 1)
            return "1";
        
        String subProblem = countAndSay(n - 1);
        int len = subProblem.length();
        
        StringBuilder result = new StringBuilder();
        
        int repetition = 1;
        char previousChar = subProblem.charAt(0);
        
        for (int i = 1; i < len; i++){
            char ch = subProblem.charAt(i);
            
            if (previousChar != ch){
                result.append(repetition);
                result.append(subProblem.charAt(i - 1));
                previousChar = ch;
                repetition = 1; 
            }
            else
                repetition++;
        }
        result.append(repetition);
        result.append(subProblem.charAt(len - 1));
        
        return result.toString();
    }
}