// { Driver Code Starts
//Initial Template for Java


import java.io.*;
import java.util.*;
class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            String S = read.readLine();
            
            Solution ob = new Solution();
            System.out.println(ob.longestSubstrDistinctChars(S));
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{
    static int longestSubstrDistinctChars(String str){
        int left = 0, right = 0;
        int maxLengthOfSubstringNonRepeatingChars = 0;

        // For 128 ASCII Characters
        int[] lastSeenAtIndexArray = new int[128];
        Arrays.fill(lastSeenAtIndexArray, -1);

        while (right < str.length()){
            char currentChar = str.charAt(right);

            if (lastSeenAtIndexArray[currentChar] != -1)
                left = Math.max(lastSeenAtIndexArray[currentChar] + 1, left);

            lastSeenAtIndexArray[currentChar] = right;

            maxLengthOfSubstringNonRepeatingChars = Math.max(right - left + 1, maxLengthOfSubstringNonRepeatingChars);
            right++;
        }
        return maxLengthOfSubstringNonRepeatingChars;
        
    }
}