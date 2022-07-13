class Solution {
    public int firstUniqChar(String s) {
        int[] count = new int[26];

        Queue<Integer> nonRepeatingCharIndices = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            count[ch - 'a']++;

            if (count[ch - 'a'] == 1)
                nonRepeatingCharIndices.add(i);

            while (!nonRepeatingCharIndices.isEmpty()  &&  count[s.charAt(nonRepeatingCharIndices.peek()) - 'a'] != 1)
                nonRepeatingCharIndices.remove();
        }
        return !nonRepeatingCharIndices.isEmpty()? nonRepeatingCharIndices.peek() : -1;
    }
    
    public int firstUniqChar_Hashing(String s) {
        int n = s.length();
        int[] count = new int[26];
        
        for (int i = 0; i < n; i++)
            count[s.charAt(i) - 'a']++;
        
        for (int i = 0; i < n; i++)
            if (count[s.charAt(i) - 'a'] == 1)
                return i;
        
        return -1;
    }
}