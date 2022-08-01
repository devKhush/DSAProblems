class Solution {
    public String longestCommonPrefix(String[] str) {
        String prefix = str[0];
        
        for (int i = 1; i < str.length; i++){
            String newPrefix = "";
            
            for (int j = 0; j < prefix.length() && j < str[i].length() && prefix.charAt(j) == str[i].charAt(j); j++)
                newPrefix += str[i].charAt(j);
            
            prefix = newPrefix;
        }
        return prefix;
    }
}