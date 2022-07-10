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
    	Stack<Integer> stack = new Stack<>();
    	
    	for (int i = 0; i < n; i++) 
    	    stack.push(i);
        
        while (stack.size() > 1){
            int A = stack.pop();
            int B = stack.pop();
            
            if (knows[A][B] == 1)
                stack.push(B);
            else
                stack.push(A);
        }
        if (stack.isEmpty()) return -1;
        
        int celebrity = stack.pop();
        
        for (int person = 0; person < n; person++)
            if (celebrity != person  &&  (knows[celebrity][person] == 1 || knows[person][celebrity] == 0))
                return -1;
                
        return celebrity;
    }
}