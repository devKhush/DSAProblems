class Solution {
    public String longestCommonPrefix(String[] str) {
        String prefix = str[0];
        
        for (int i = 1; i < str.length; i++){
            StringBuilder newPrefix = new StringBuilder();
            
            for (int j = 0; j < prefix.length() && j < str[i].length() && prefix.charAt(j) == str[i].charAt(j); j++)
                newPrefix.append(prefix.charAt(j));
            
            prefix = newPrefix.toString();
        }
        return prefix;
    }
}