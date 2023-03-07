package Hashing.VerifyingAnAlienDictionary;

public class VerifyingAnAlienDictionary {
    /*
    * Time Complexity: O(n * m)
        * n -> size of words array
        * m -> average length of each words
    * Space Complexity: O(1)
     */
    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];
        for (int i = 0; i < 26; i++){
            map[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; i++){
            if (!areSorted(words[i], words[i + 1], map))
                return false;
        }
        return true;
    }

    private boolean areSorted(String s1, String s2, int[] map){
        int n = s1.length(), m = s2.length();

        for (int i = 0; i < Math.min(n, m); i++){
            if (map[s1.charAt(i) - 'a'] < map[s2.charAt(i) - 'a'])
                return true;
            else if (map[s1.charAt(i) - 'a'] > map[s2.charAt(i) - 'a'])
                return false;
        }
        if (n > m) return false;
        return true;
    }
}
