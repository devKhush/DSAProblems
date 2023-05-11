package Strings.MaximumNumberOfVowelsInSubstringOfGivenLength;

// https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/

public class MaximumNumberOfVowelsInSubstringOfGivenLength {
    /********************************* Simple Intuitive Solution ************************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int maxVowels(String s, int k) {
        int maxVowels = 0;
        int vowels = 0;
        int n = s.length();

        for (int i = 0; i < k; i++){
            if (isVowel(s.charAt(i))) vowels++;
        }
        maxVowels = vowels;

        for (int i = k; i < n; i++){
            if (isVowel(s.charAt(i - k))) vowels--;
            if (isVowel(s.charAt(i))) vowels++;
            maxVowels = Math.max(maxVowels, vowels);
        }
        return maxVowels;
    }

    private boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
