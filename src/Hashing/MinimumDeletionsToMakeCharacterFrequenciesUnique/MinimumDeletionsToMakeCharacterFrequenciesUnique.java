package Hashing.MinimumDeletionsToMakeCharacterFrequenciesUnique;
import java.util.Arrays;

// https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/description/

public class MinimumDeletionsToMakeCharacterFrequenciesUnique {
    /************************************** Hashing Solution ***************************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int minDeletions(String s) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++){
            count[s.charAt(i) - 'a']++;
        }
        Arrays.sort(count);

        int deletions = 0;
        for (int i = 24; i >= 0; i--){
            if (count[i] == 0) break;
            if (count[i] >= count[i + 1]){
                int prev = count[i];
                count[i] = Math.max(0, count[i + 1] - 1);
                deletions += prev - count[i];
            }
        }
        return deletions;
    }
}
