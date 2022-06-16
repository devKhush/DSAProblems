package Strings.ValidPalindrome;

class ValidPalindrome {

    // ************************** Iterative Code for Palindrome Check ****************************
    // TC -> O(n/2) = O(n)  In worst case
    // SC -> O(1)
    private boolean isPalindrome_Iterative(String str, int low, int high){
        while (low <= high){
            if (str.charAt(low) != str.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }

    // ************************** Recursive Code for Palindrome Check ****************************
    // TC -> O(n/2) = O(n)  In worst case
    // SC -> O(n/2) = O(n)  In worst case
    private boolean isPalindrome_Recursive(String s, int low, int high){
        if (low > high  || low == high)
            return true;
        return s.charAt(low) == s.charAt(high)  &&  isPalindrome_Recursive(s, low+1, high-1);
    }


    // *********************************** Brute Force ****************************************
    // We are using one for loop that accounts for one O(n) & "s.charAt(i)" also takes O(n) time. T
    // These two nested operations cause O(n^2) TC.
    // Another O(n) for palindrome check
    // TC -> O(n^2) + O(n)
    // SC -> O(n)        for creating a new String that is valid
    public boolean isPalindrome_BruteForce(String s) {
        String str = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ((ch >='a' && ch <= 'z') || (ch >= '0' && ch <= '9'))
                str += ch;
            if (ch >= 'A' && ch <= 'Z')
                str += (char)(ch - 'A' + 'a');
        }

        return isPalindrome_Iterative(str, 0, str.length() - 1);
        // return isPalindrome_Recursive(str, 0, str.length() - 1);
    }


    // *********************************** Optimal Approach ****************************************
    // We are using one for loop that accounts for one O(n) & s[i] character indexing in arrays
    // takes O(1) time. Another O(n) for palindrome check
    // TC -> O(n) + O(n)
    // For creating a new char array from original string & a new StringBuilder that stored valid string.
    // STRING BUILDERS ARE FASTER THAN STRINGS
    // SC -> O(n)

    public boolean isPalindrome_Best(String string) {
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
}