class Solution {
    public int firstUniqChar(String s) {
        char[] count = new char[26];
        
        for (int i=0; i<s.length(); i++){
            count[s.charAt(i) - 'a']++;
        }
        
        for (int i=0; i<s.length(); i++){
            if (count[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
}