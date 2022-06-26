// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main(String[] args) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t-->0)
        {
            StringTokenizer stt = new StringTokenizer(br.readLine());
            
            int n = Integer.parseInt(stt.nextToken());
            int m = Integer.parseInt(stt.nextToken());
            int k = Integer.parseInt(stt.nextToken());
            int a[] = new int[(int)(n)];
            int b[] = new int[(int)(m)];
            
            
            String inputLine[] = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(inputLine[i]);
            }
            String inputLine1[] = br.readLine().trim().split(" ");
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(inputLine1[i]);
            }
            
            
            Solution obj = new Solution();
            System.out.println(obj.kthElement( a, b, n, m, k));
            
        }
	}
}
// } Driver Code Ends


//User function Template for Java


class Solution {
    public long kthElement( int A[], int B[], int n1, int n2, int k){
        int m = A.length, n = B.length;
        if (m > n) return kthElement(B, A, -1, -1,k);
        
        int low = Math.max(0, k-n), high = Math.min(k, m);

        while (low <= high){
            int cutA = (low + high)/2;
            int cutB = k - cutA;
            
            int leftA = cutA == 0 ? Integer.MIN_VALUE : A[cutA - 1];
            int leftB = cutB == 0 ? Integer.MIN_VALUE : B[cutB - 1];
            int rightA = cutA == m ? Integer.MAX_VALUE : A[cutA];
            int rightB = cutB == n ? Integer.MAX_VALUE : B[cutB];
            
            if (leftA <= rightB && leftB <= rightA)
                return Math.max(leftA, leftB);
            if (leftA > rightB)
                high = cutA - 1;
            else if (leftB > rightA)
                low = cutA + 1;
        }        
        return 1l;
    }
}