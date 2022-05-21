package Strings.IsSubsequence;

// https://leetcode.com/problems/is-subsequence/discuss/1811180/C%2B%2B-oror-Easy-oror-3-Approaches-oror-Brute-Force-oror-Recursive-oror-Memoization

class IsSubsequence {
    public boolean isSubsequence(String s, String t) {    
        int i = 0;
        
        for (int j = 0; j < t.length() && i < s.length(); j++){
            if (s.charAt(i) == t.charAt(j))
                i++;
        }
        return i == s.length();
    }
}