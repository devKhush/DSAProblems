class Solution {
    public String countAndSay(int n) {
        if (n == 1)
            return "1";
        
        String subProblem = countAndSay(n - 1);
        int len = subProblem.length();
        
        StringBuilder result = new StringBuilder();
        int repetition = 0;
        
        for (int i = 0; i < len; i++){
            char ch = subProblem.charAt(i);
            
            if (i != 0  &&  subProblem.charAt(i - 1) != ch){
                result.append(repetition);
                result.append(subProblem.charAt(i - 1));
                repetition = 0; 
            }
            repetition++;
        }
        result.append(repetition);
        result.append(subProblem.charAt(len - 1));
        
        return result.toString();
    }
}