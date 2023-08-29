package Strings.MinimumPenaltyForShop;

// https://leetcode.com/problems/minimum-penalty-for-a-shop/editorial/

public class MinimumPenaltyForShop {
    /*********************************** Prefix-Suffix Sum ********************************************
     * Time Complexity: O(2*n)
     * Space Complexity: O(1)
     */
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int suffixPenalty = 0, prefixPenalty = 0;
        for (int i = 0; i < n; i++){
            if (customers.charAt(i) == 'Y')
                suffixPenalty++;
        }
        int minPenalty = suffixPenalty;
        int hourClosed = 0;
        for (int i = 0; i < n; i++){
            if (customers.charAt(i) == 'Y') suffixPenalty--;
            else prefixPenalty++;
            if (minPenalty > suffixPenalty + prefixPenalty){
                minPenalty = suffixPenalty + prefixPenalty;
                hourClosed = i + 1;
            }
        }
        return hourClosed;
    }
}
