class Solution {
    
    public int lengthOfLongestSubstring(String str) {
        int left = 0, right = 0;
        int maxLengthOfSubstringWithoutRepeatingChars = 0;

        HashMap<Character, Integer> lastSeenAtIndexMap = new HashMap<>();
        
        while (right < str.length()){
            
            if (lastSeenAtIndexMap.containsKey(str.charAt(right)))
                if (lastSeenAtIndexMap.get(str.charAt(right)) +1 > left)
                    left = Math.max(lastSeenAtIndexMap.get(str.charAt(right)) + 1, left);
            
            lastSeenAtIndexMap.put(str.charAt(right), right);
            maxLengthOfSubstringWithoutRepeatingChars = Math.max(right - left + 1, maxLengthOfSubstringWithoutRepeatingChars);
            right++;
        }
        
        return maxLengthOfSubstringWithoutRepeatingChars;
    }
    
    
    // Hash Set Solution **********************************************************************
    public int lengthOfLongestSubstring_Hashing(String str) {
        int left = 0, right = 0;
        int maxLengthOfSubstringWithoutRepeatingChars = 0;

        HashSet<Character> allUniqueCharacters = new HashSet<>();
        
        while (right < str.length()){
            
            if (allUniqueCharacters.contains(str.charAt(right))){
                while (allUniqueCharacters.contains(str.charAt(right))){
                    allUniqueCharacters.remove(str.charAt(left));
                    left++;
                }
            }
            
            allUniqueCharacters.add(str.charAt(right));
            maxLengthOfSubstringWithoutRepeatingChars = Math.max(right - left + 1 , maxLengthOfSubstringWithoutRepeatingChars);
            right++;
        }
        return maxLengthOfSubstringWithoutRepeatingChars;
    }
}