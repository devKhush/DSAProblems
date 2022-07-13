package Queues.FirstNonRepeatingCharacter;
import java.util.HashMap;

// https://www.youtube.com/watch?v=5co5Gvp_-S0

class FirstNonRepeatingCharacter {
    /* ******************************* Brute Force ********************************************
     * Intuition: For each character in the string check whether it is the first one or not.
                  If it is the first non-repeating character, return the index
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int firstUniqChar_BruteForce(String s) {
        int n = s.length();

        // Checking for each character whether it is duplicate or not
        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);

            // checking in the whole String whether "Current character" is the first non-repeating char or not
            int j;
            for (j = 0; j < n; j++)
                if (i != j  &&  ch == s.charAt(j))      // if it is repeating, it is definitely not the first non-repeating char
                    break;

            if (j == n)
                return i;
        }
        return -1;
    }


    // ************************************ Hashing Solution ******************************************
    // Time Complexity :  O(n) + O(n)  =  O(n)
    // Space Complexity:  O(26) = O(1)
    public int firstUniqChar_HashMap(String s) {
        int n = s.length();
        HashMap<Character, Integer> characterCountMap = new HashMap<>();

        for (int i = 0; i < n; i++){
            char ch = s.charAt(i);
            characterCountMap.put(ch, characterCountMap.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < n; i++)
            if (characterCountMap.get(s.charAt(i)) == 1)
                return i;
        return -1;
    }


    // ******************************** Hashing using Array Solution **********************************
    // Time Complexity :  O(n) + O(n)  =  O(n)
    // Space Complexity:  O(26) = O(1)
    public int firstUniqChar_ArrayHashing(String s) {
        int n = s.length();
        int[] charCount = new int[26];

        for (int i = 0; i < n; i++)
            charCount[s.charAt(i) - 'a']++;

        for (int i = 0; i < n; i++)
            if (charCount[s.charAt(i) - 'a'] == 1)
                return i;

        return -1;
    }
}