class Solution {
    public String longestCommonPrefix_V1(String[] str) {
        String prefix = str[0];

        for (int i = 1; i < str.length; i++){
            StringBuilder newPrefix = new StringBuilder();
            int minLength = Math.min(prefix.length(), str[i].length());

            for (int j = 0; j < minLength  &&  prefix.charAt(j) == str[i].charAt(j); j++)
                newPrefix.append(prefix.charAt(j));

            prefix = newPrefix.toString();
        }
        return prefix;
    }
    
     public String longestCommonPrefix(String[] str){
        String prefix = str[0];

        for (int i = 1; i < str.length; i++){
            while (!str[i].startsWith(prefix))
                prefix = prefix.substring(0, prefix.length() - 1);
        }
        return prefix;
    }
}