package Arrays.MaximumSumRectangle_Kadane2D;

// KADANE's 2D Algorithm
// Somewhat similar to Max sum in rectangle No larger than K (in Arrays)

public class MaximumSumRectangle {
    int maximumSumRectangle(int R, int C, int arr[][]) {
        int maxSum = Integer.MIN_VALUE;
        int[] temp = new int[C]; 
        
        for (int i=0; i<R; i++){
            for (int k=i; k<R; k++){
                
                for (int j=0; j<C; j++)
                    temp[j] += arr[k][j];
                
                maxSum = Math.max(maxSum, kadane1D(temp, C));
            }
            
            for (int j=0; j<C; j++)
                temp[j] = 0;
        }
        return maxSum;
    }
 
    private int kadane1D(int[] arr, int n){
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int val: arr){
            sum += val;
            
            if (sum > maxSum)
                maxSum = sum;
                
            if (sum < 0)
                sum = 0;
        }
        return maxSum;
    }
}