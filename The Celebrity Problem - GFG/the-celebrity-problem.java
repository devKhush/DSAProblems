// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*; 

class GFG{
    public static void main(String args[]) throws IOException { 
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t>0)
        {
            int N = sc.nextInt();
            int M[][] = new int[N][N];
            for(int i=0; i<N; i++)
            {
                for(int j=0; j<N; j++)
                {
                    M[i][j] = sc.nextInt();
                }
            }
            System.out.println(new Solution().celebrity(M,N));
            t--;
        }
    } 
} // } Driver Code Ends


//User function Template for Java


class Solution
{ 
    //Function to find if there is a celebrity in the party or not.
    int celebrity(int knows[][], int n){
        int personA = 0, personB = n - 1;
        while (personA < personB){
            if (knows[personA][personB] == 1)
                personA++;
            else 
                personB--;
        }
        int celebrity = personA;

        for (int person = 0; person < n; person++)
            if (celebrity != person  &&  (knows[celebrity][person] == 1 || knows[person][celebrity] == 0))
                return -1;

        return celebrity;
    }
}