// { Driver Code Starts
import java.util.*;

class MaxLenZeroSumSub
{

    // Returns length of the maximum length subarray with 0 sum

    // Drive method
    public static void main(String arg[])
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T > 0)
        {
            int n = sc.nextInt();
            int arr[] = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();

            GfG g = new GfG();
            System.out.println(g.maxLen(arr, n));
            T--;
        }
    }
}// } Driver Code Ends


class GfG{
    int maxLen(int arr[], int n){
        int maxSubArrayLength = 0;
        int prefixSum = 0;
        
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++){
            prefixSum += arr[i];

            // we don't meed Math.max() condition here, as if the entire prefix sum becomes 0 at any index 'i'
            // then it will be of maximum length till index 'i' (think)
            if (prefixSum == 0)
                maxSubArrayLength = i + 1;


            if (prefixSumMap.containsKey(prefixSum)) {
                int startIndexOfSum = prefixSumMap.get(prefixSum);
                int endingIndexOfSameSum = i;
                maxSubArrayLength = Math.max(maxSubArrayLength, endingIndexOfSameSum - startIndexOfSum);
            }
            
            else
                prefixSumMap.put(prefixSum, i);
        }
        return maxSubArrayLength;
    }
    
    
    
    
    int maxLen_BruteForce(int arr[], int n){
        int len = 0;
        
        for (int i = 0; i < n; i++){
            int sum = 0;
            
            for (int j = i; j < n; j++){
                sum += arr[j];
                
                if (sum == 0)
                    len = Math.max(len, j - i + 1);
            }
        }
        return len;
    }
}