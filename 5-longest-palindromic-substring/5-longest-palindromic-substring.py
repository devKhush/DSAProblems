class Solution:
    def longestPalindrome(self, s: str) -> str:
        long_palindrome_length: int = 0
        long_palindrome: str = ""

        for i in range(len(s)):

            # checks for even length palindrome (not for even length string)
            low, high = i, i + 1
            while low >= 0 and high < len(s) and s[low] == s[high]:
                if high - low + 1 > long_palindrome_length:
                    long_palindrome_length = high - low + 1
                    long_palindrome = s[low: high + 1]
                low -= 1
                high += 1

            # checks for odd length palindrome (not for odd length string)
            low, high = i, i
            while low >= 0 and high < len(s) and s[low] == s[high]:
                if high - low + 1 > long_palindrome_length:
                    long_palindrome_length = high - low + 1
                    long_palindrome = s[low: high+1]
                low -= 1
                high += 1

        return long_palindrome
        