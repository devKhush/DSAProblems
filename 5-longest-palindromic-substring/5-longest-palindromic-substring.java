class Solution {
    public String longestPalindrome(String string) {
        int n = string.length();
        char[] s = string.toCharArray();

        int longestPalindromeLength = 0;
        int palindromeStartIndex = 0, palindromeEndIndex = 0;

        for (int i = 0; i < s.length; i++){
            int len1 = expandFromMiddle(s, i, i);
            int len2 = expandFromMiddle(s, i, i + 1);
            int currPalindromeExpandedLength = Math.max(len1, len2);
            
            if (currPalindromeExpandedLength > longestPalindromeLength){
                longestPalindromeLength = currPalindromeExpandedLength;
                palindromeStartIndex = i - (currPalindromeExpandedLength - 1)/2;
                palindromeEndIndex = i + currPalindromeExpandedLength/2;
            }
        }
        return string.substring(palindromeStartIndex, palindromeEndIndex + 1);
    }

    
    private int expandFromMiddle(char[] str, int index1, int index2){
        int left = index1, right = index2;

        while (left >= 0  &&  right < str.length  &&  str[left] == str[right]){
            left--;
            right++;
        }
        return right - left - 1;
    }
    
    
    
    // DP Solution ******************************************************************************
    public String longestPalindrome_DP(String str) {
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