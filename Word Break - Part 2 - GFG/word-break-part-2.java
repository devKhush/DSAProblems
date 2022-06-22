// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
import java.util.stream.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int n = Integer.parseInt(in.readLine());
            String arr[] = in.readLine().trim().split("\\s+");
            List<String> dict = new ArrayList<String>();
            for(int i = 0;i < n;i++)
                dict.add(arr[i]);
            String s = in.readLine();
            
            Solution ob = new Solution();
            List<String> ans = new ArrayList<String>();
            ans = ob.wordBreak(n, dict, s);
            if(ans.size() == 0)
                System.out.println("Empty");
            else{
                List<String> sol = ans.stream().sorted().collect(Collectors.toList());
                for(int i = 0;i < sol.size();i++)
                    System.out.print("("+sol.get(i)+")");
                System.out.println();
            }
        }
    }
}// } Driver Code Ends


//User function Template for Java

class Solution{
    static List<String> wordBreak(int n, List<String> dict, String s){
        ArrayList<String> allBrokenWords = new ArrayList<>();

        breakWord(0, s, s.length(), dict, new ArrayList<>(), allBrokenWords);
        return allBrokenWords;
    }
    
    static void breakWord(int index, String s, int n, List<String> dict, 
    ArrayList<String> currentBrokenWord, ArrayList<String> allBrokenWords){
        if (index == n){
            allBrokenWords.add(String.join(" ", currentBrokenWord));
            return;
        }
        
        for (int i = index; i < n; i++){
            String str = s.substring(index, i + 1);
            if (dict.contains(str)){
                currentBrokenWord.add(str);
                breakWord(i + 1, s, n, dict, currentBrokenWord, allBrokenWords);
                currentBrokenWord.remove(currentBrokenWord.size() -1 );
            }
        }
    } 
}