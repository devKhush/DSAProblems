class Solution {
   public boolean isPalindrome(String string) {
        StringBuilder str = new StringBuilder();
        char[] s = string.toCharArray();

        for (int i = 0; i < s.length; i++) {
            char ch = s[i];
            if ((ch >='a' && ch <= 'z') || (ch >= '0' && ch <= '9'))
                str.append(ch);
            if (ch >= 'A' && ch <= 'Z')
                str.append((char) (ch - 'A' + 'a'));
        }

        return isPalindrome_Iterative(str.toString(), 0, str.length() - 1);
    }
    
   private boolean isPalindrome_Iterative(String str, int low, int high){
        while (low <= high){
            if (str.charAt(low) != str.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }
}