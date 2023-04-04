package Strings.FindTheSubstringWithMaximumCost;

// Algorithm used: "Kadane Algorithm"

public class FindTheSubstringWithMaximumCost {
    // TC -> O(n)
    // SC -> O(26) = O(1)
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int n = s.length();

        int[] map = new int[26];
        for (int i = 0; i < 26; i++) {
            map[i] = i + 1;
        }
        for (int i = 0; i < chars.length(); i++) {
            map[chars.charAt(i) - 'a'] = vals[i];
        }

        int maxSum = 0;
        int currSum = 0;
        for (int i = 0; i < n; i++) {
            currSum += map[s.charAt(i) - 'a'];

            if (currSum < 0)
                currSum = 0;
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }
}
