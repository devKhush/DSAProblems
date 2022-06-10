// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;

class Main{
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int s = sc.nextInt();

            int[] m = new int[n];
            for (int j = 0; j < n; j++) {
                m[j] = sc.nextInt();
            }
            
            Solution obj = new Solution();
            ArrayList<Integer> res = obj.subarraySum(m, n, s);
            for(int ii = 0;ii<res.size();ii++)
                System.out.print(res.get(ii) + " ");
            System.out.println();
        }
    }

}// } Driver Code Ends


class Solution{
   
    public static ArrayList<Integer> subarraySum(int[] arr, int n, int sum) {
      ArrayList<Integer> answer = new ArrayList<>();
      HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
      int prefixSum = 0;
      
      for (int i = 0; i < arr.length; i++){
          prefixSum += arr[i];
          
          if (prefixSum == sum){
              answer.add(1);
              answer.add(i + 1);
              return answer;
          }
          
          if (prefixSumMap.containsKey(prefixSum - sum)) {
              answer.add(prefixSumMap.get(prefixSum - sum) + 2);
              answer.add(i + 1);
              return answer;
          }
          
          prefixSumMap.put(prefixSum, i);
      }
      
      answer.add(-1);
      return answer;
    }
    
}