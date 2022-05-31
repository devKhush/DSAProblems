// { Driver Code Starts
//Initial template for Java

import java.io.*;
import java.util.*;


 // } Driver Code Ends
//User function template for Java

class Solution{
    public static void sort012(int a[], int n){
        int count0 = 0;
        int count1 = 0;
        // int count2 = 0;
        
        for (int val : a){
            if (val==0) count0++;
            if (val==1) count1++;
            // if (val==2) count2++;
        }
        
        int i = 0;
        
        while (i < count0)
            a[i++] = 0;
            
        while (i < count0  + count1)
            a[i++] = 1;
            
        // We don't need a variable that stores count of 2, as array contains 
        // only 0s, 1s & 2s (counts of 0s & 1s are already stored)
        // while (i < count0  + count1  + count2)
        //     a[i++] = 2;
        
        while (i < n)
            a[i++] = 2;
            
    }
}

// { Driver Code Starts.

class GFG {
    
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
        while(t-->0){
            int n = Integer.parseInt(br.readLine().trim());
            int arr[] = new int[n];
            String inputLine[] = br.readLine().trim().split(" ");
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(inputLine[i]);
            }
            Solution ob=new Solution();
            ob.sort012(arr, n);
            StringBuffer str = new StringBuffer();
            for(int i=0; i<n; i++){
                str.append(arr[i]+" ");
            }
            System.out.println(str);
        }
    }
}

  // } Driver Code Ends