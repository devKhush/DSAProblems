package DynamicProgramming.DP_on_Strings.LongestCommonSubsequences;

// https://youtu.be/NPZn9jBrX8U
// https://takeuforward.org/data-structure/longest-common-subsequence-dp-25/

public class LCS_Recursion {

    // ********************************* Simple Recursion **********************************************
    // At every index of string1 & string2 we are calling recursive function at most two times.
    // So, we are trying out all the possibilities/subsequences of both the string.
    // So, there are 2^m & s^n subsequences of string1 & string2 respectively
    // Time complexity : O(2^n * 2^m)   Exponential

    // At max, we can reach base condition of length m & n of both string
    // We are using an auxiliary recursion stack space of O(N+M)
    // (see the recursive tree in notes, in the worst case, we will go till N+M calls at a time)
    // Space Complexity : O(max(m, n))

    private int getLongestCommonSubsequence_V1(String s1, String s2){
        int m = s1.length();
        int n = s2.length();

        // LCS_Recursion_V1(m-1, n-1) means the longest common subsequence of string1[0:m-1] and string[0:n-1]
        // both lower & upper bound included
        return LCS_Recursion_V1(m-1, n-1, s1, s2);
    }

    // LCS_Recursion_V1(i, j) means the longest common subsequence of string1[0:i] and string[0:j]
    // upper bound included in function call
    private int LCS_Recursion_V1(int i, int j, String s1, String s2){
        // Maximum negative value of i & j will be -1
        if (i < 0  || j < 0)
            return 0;

        // Case When the characters of current indices in both the string are equal
        if (s1.charAt(i) == s2.charAt(j))
            return  1 + LCS_Recursion_V1(i-1, j-1, s1, s2);

        // When the characters of current indices in both the string are not equal
        // Then return the maximum of length of LCS found by decreasing both the indices one by one
        return 0 + Math.max(LCS_Recursion_V1(i-1, j, s1, s2), LCS_Recursion_V1(i,j-1, s1, s2));
    }




    // **********************************************************************************************
    // Another version of Recursion Solution By Shifting of Index

    private int getLongestCommonSubsequence_V2(String s1, String s2){
        int m = s1.length();
        int n = s2.length();

        // LCS_Recursion_V2(m, n) means the longest common subsequence of string1[0:m] and string[0:n]
        // upper bound not included (just like in string indexing)
        return LCS_Recursion_V2(m, n, s1, s2);
    }

    // LCS_Recursion_V2(i, j) means the longest common subsequence of string1[0:i] and string[0:j]
    // upper bound not included (just like in string indexing)
    private int LCS_Recursion_V2(int i, int j, String s1, String s2){
        // Min. value of i & j will be 0
        if (i == 0  || j == 0)
            return 0;

        // Case When the characters of current indices in both the string are equal
        if (s1.charAt(i-1) == s2.charAt(j-1))
            return  1 + LCS_Recursion_V2(i-1, j-1, s1, s2);

        // When the characters of current indices in both the string are not equal
        // Then return the maximum of length of LCS found by decreasing both the indices one by one
        return 0 + Math.max(LCS_Recursion_V2(i-1, j, s1, s2), LCS_Recursion_V2(i,j-1, s1, s2));
    }


}
