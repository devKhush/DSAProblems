class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int[] count = new int[26];
        
        for (char ch: letters)
            count[ch - 'a']++;
        
        int indexChar = target - 'a';
        
        for (int i = indexChar + 1; i < 26; i++){
            if (count[i] != 0)
                return (char) (i + 'a');
        }
        
        return letters[0];
    }
}