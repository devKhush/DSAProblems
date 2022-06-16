class Solution {
   public boolean isPalindrome(String s) {
        String str = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ((ch >='a' && ch <= 'z') || (ch >= '0' && ch <= '9'))
                str += ch;
            if (ch >= 'A' && ch <= 'Z')
                str += (char)(ch - 'A' + 'a');
        }
        System.out.println(str);
       
        int low = 0;
        int high = str.length() - 1;
        while (low <= high){
            if (str.charAt(low) != str.charAt(high))
                return false;
            low++;
            high--;
        }
       return true;
        // return isPalindrome_Recursive(str, 0, str.length() - 1);
    }
    
    private boolean isPalindrome_Recursive(String s, int low, int high){
        if (low > high  || low == high)
            return true;
        return s.charAt(low) == s.charAt(high)  &&  isPalindrome_Recursive(s, low+1, high-1);
    } 
}