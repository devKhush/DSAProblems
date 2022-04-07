package Queues.FirstNonRepeatingCharacter;
import java.util.HashMap;

// https://www.youtube.com/watch?v=5co5Gvp_-S0

class FirstNonRepeatingCharacter {

    // O(n^2) Brute force
    public int firstUniqChar_O_NSquare(String s) {
        
        for (int i=0; i<s.length(); i++){
            boolean seenDuplicate = false;
            
            for (int j = 0; j<s.length(); j++){
                if (i!=j && s.charAt(i)==s.charAt(j)){
                    seenDuplicate = true;
                    break;
                }
            }
            if (!seenDuplicate)             // if (j==s.length())    condition also works
                return i;
        }
        
        return -1;
    }

    // O(n)
    public int firstUniqChar_2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        for (int i=0; i<s.length(); i++){
            if (map.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    // DP Approach (much faster)
    public int firstUniqChar_3(String s) {
        char[] charCount = new char[26];
        for (int i=0; i<s.length(); i++)
            charCount[s.charAt(i) - 'a']++;

        for (int i=0; i<s.length(); i++){
            if (charCount[s.charAt(i) - 'a'] == 1)
                return i;
        }

        return -1;
    }

}