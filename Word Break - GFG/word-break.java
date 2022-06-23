// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
public class GfG
{
    public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    int n;
                    n = sc.nextInt();
                    ArrayList<String> arr = new ArrayList<String>();
                    for(int i = 0;i<n;i++)
                        {
                            String p = sc.next();
                            arr.add(p);
                        }
                    String line = sc.next();
                    Sol obj = new Sol();  
                    System.out.println(obj.wordBreak(line,arr));  
                    
                }
        }
}// } Driver Code Ends


//User function Template for Java

class Sol{
    public static int wordBreak(String s, ArrayList<String> wordDict ){
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        
        for (int index = n - 1; index >= 0; index--){
            for (int i = index; i < n; i++){
                if (wordDict.contains(s.substring(index, i + 1)) &&  dp[i + 1]){
                    dp[index] = true;
                    break;
                }
            }
        }
        return dp[0] ? 1 : 0;
    }
}