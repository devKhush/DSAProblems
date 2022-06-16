class Solution {
    public String longestPalindrome(String str) {
        int n = str.length();
        int maxPalindromeLength = 0;
        int palindromeStartIndex = 0, palindromeEndIndex = 0;
        char[] s = str.toCharArray();

        boolean[][] dp = new boolean[n][n];
        
        for (int i = n-1; i >= 0; i--){
            for (int j = i; j < n; j++){
                dp[i][j] =  (s[i] == s[j])  &&  (j - i + 1 <= 2 || dp[i+1][j-1]);

                if (dp[i][j]  &&  j - i + 1 > maxPalindromeLength){
                    maxPalindromeLength = j - i + 1;
                    palindromeStartIndex = i;
                    palindromeEndIndex = j;
                }
            }
        }
        return str.substring(palindromeStartIndex, palindromeEndIndex + 1);
    }
}