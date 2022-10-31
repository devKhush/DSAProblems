//{ Driver Code Starts
//Initial Template for Java
import java.util.*;
import java.io.*;
class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(read.readLine());
            Solution ob = new Solution();

            System.out.println(ob.armstrongNumber(n));
        }
    }
}
// } Driver Code Ends


//User function Template for Java
class Solution {
    static String armstrongNumber(int n){
        int N = n;
        int count = 0;
        while (N > 0){
            count++;
            N /= 10;
        }
        
        int arm = 0;
        N = n;
        while (N > 0){
            int d = N % 10;
            arm += Math.pow(d, count);
            N /= 10;
        }
        return arm == n ? "Yes" : "No";
    }
}