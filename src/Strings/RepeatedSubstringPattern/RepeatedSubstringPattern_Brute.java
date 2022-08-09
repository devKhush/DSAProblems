package Strings.RepeatedSubstringPattern;

// https://youtu.be/bClIZj66dVE
// https://youtu.be/lumwBLJOQpk

class RepeatedSubstringPattern_Brute {
    /********************************* Brute Force *************************************************
     * Brute Force Idea is to check whether each substring in the string can be repeated required no.
        of times to obtain the whole input string.

     * Condition for Substring to repeat itself to form input string
        * Say, length(input_string) = n
        * Say, length(substring that can be repeated to form input string) = k
        * Clearly, k <= n
        * The "n / k" will be no. of times substring has to be repeated to produce input string.
        * Also, n % k == 0. The "n" must be a multiple of "k", as no. of repetitions has to be an
            integer number.

     * Time Complexity: O(n^2)  in Worst case
                        O(n)    in Best Case
        * TC is O(n^2) in worst case, due to nested loops
        * In case of string that can be formed by repeating the substring, time complexity will be
            nearly O(n)
     * Space Complexity: O(n)
        * For String builder used for repeating the string
     */
    public boolean repeatedSubstringPattern_Brute(String s) {
        int n = s.length();

        // If we loop from backwards, then time taken for the case of strings that can actually be
        // repeated will be nearly O(n), as the half-length substring can also be repeated to get
        // the entire string. Think...
        // for (int i = 1; i <= n/2; i++){
        for (int i = n/2; i >= 1; i--){

            // If the length of current substring, "i" is factor of "n", only then current substring
            // (from index 0 to index (i-1)) can be repeated to obtain the entire input string
            // See condition
            if (n % i == 0){
                // Get the substring from start index 0 to index (i-1)
                String subString = s.substring(0, i);

                // No. of times to repeat to obtain entire input string. See condition
                int repeatTimes = n / i;

                // Repeat the substring required no. of times
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < repeatTimes; j++)
                    sb.append(subString);

                // Check if after repeated substring is same as input string. If yes, return true
                if (s.equals(sb.toString()))
                    return true;
            }
        }
        return false;
    }

    // Program to Check whether two String are equal to not
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    private boolean equals(String s1, String s2){
        int n = s1.length();
        int i = 0, j = 0;
        
        while (i < n && j < n){
            if (s1.charAt(i) != s2.charAt(j))
                return false;
            
            i++;
            j++;
        }
        return true;
    }
}