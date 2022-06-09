// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

public class GFG {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            inputLine = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine[0]);
            int k = Integer.parseInt(inputLine[1]);
            int[] arr = new int[n];
            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }
            int ans = new Solution().getPairsCount(arr, n, k);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution {
    
    int getPairsCount(int[] arr, int n, int sum) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int val : arr){
            if (map.containsKey(sum - val))
                count += map.get(sum - val);
                
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
    
        return count;
    }
    
    int getPairsCount_Sorting(int[] arr, int n, int sum) {
        int count = 0;
        
        Arrays.sort(arr);
        int low = 0, high = n-1;
        
        while (low < high){
            if (arr[low] + arr[high] == sum){
                count++;
                high--;
            }
            else if (arr[low] + arr[high] < sum)
                low++;
            else if (arr[low] + arr[high] > sum)
                high--;
        }
        return count;
    }
    
    
    int getPairsCount_BruteForce(int[] arr, int n, int sum) {
        int count = 0;
        
        for (int i = 0; i < n; i++)
            for (int j = i+1; j < n; j++)
                if (arr[i] + arr[j] == sum)
                    count++;
    
        return count;
    }
    
}
