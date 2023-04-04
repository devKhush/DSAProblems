package Strings.OptimalPartitionOfString;
import java.util.Arrays;

public class OptimalPartitionOfString {
    /************************************** Solution 1 ********************************************
     * TC -> O(n)
     * SC -> nearly O(1)
        * not exactly O(1) bcoz we keep on reinitializing array
     * */
    public int partitionString(String s) {
        boolean[] seenCharacter = new boolean[26];
        int substrings = 1;

        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if (seenCharacter[ch - 'a']){
                substrings++;
                seenCharacter = new boolean[26];
            }
            seenCharacter[ch - 'a'] = true;
        }
        return substrings;
    }


    /********************************** Faster solution 2 *************************************
     * TC -> O(n)
     * SC -> O(1)
     **/
    public int partitionString_Best(String s) {
        int[] seenCharAt = new int[26];
        Arrays.fill(seenCharAt, -1);

        int substrings = 1;
        int subStringStart = 0;           // we keep track of the index where new substring is starting

        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if (seenCharAt[ch - 'a'] >= subStringStart){
                substrings++;
                subStringStart = i;
            }
            seenCharAt[ch - 'a'] = i;
        }
        return substrings;
    }
}
