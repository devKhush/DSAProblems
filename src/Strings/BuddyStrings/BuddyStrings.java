package Strings.BuddyStrings;

// https://leetcode.com/problems/buddy-strings/description/

public class BuddyStrings {
    /*************************************** HashMap Solution *****************************************
     * Time Complexity: O(n)
     * Time Complexity: O(26) ~ O(1)
     */
    public boolean buddyStrings(String s, String goal) {
        int m = s.length();
        int n = goal.length();
        if (m != n)
            return false;

        int unequal = 0;
        int[] hash1 = new int[26];
        int[] hash2 = new int[26];
        for (int i = 0; i < n; i++){
            hash1[s.charAt(i) - 'a']++;
            hash2[goal.charAt(i) - 'a']++;
            if (s.charAt(i) != goal.charAt(i))
                unequal++;
        }

        boolean duplicate = false;
        for (int i = 0; i < 26; i++){
            if (hash1[i] != hash2[i])
                return false;
            if (hash1[i] > 1)
                duplicate = true;
        }
        return unequal == 2 || (unequal == 0 && duplicate);
    }
}
