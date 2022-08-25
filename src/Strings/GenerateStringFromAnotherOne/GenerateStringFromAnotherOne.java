package Strings.GenerateStringFromAnotherOne;

class GenerateStringFromAnotherOne {
    /*
    * m -> length of String to generate
    * n -> length of string to be used for generating
    * Time Complexity: O(m + n)
    * Space Complexity: O(n)
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] count = new int[26];
        
        for (int i = 0; i < magazine.length(); i++){
            count[magazine.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < ransomNote.length(); i++){
            char ch = ransomNote.charAt(i);

            if (count[ch - 'a'] <= 0)
                return false;
            count[ch - 'a']--;
        }
        return true;
    }
}