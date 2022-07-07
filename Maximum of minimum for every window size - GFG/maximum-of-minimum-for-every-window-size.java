// { Driver Code Starts
import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            String[] inputline = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputline[0]);
            inputline = br.readLine().trim().split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputline[i]);
            }
            Solution ob =new Solution();
            int[] res = ob.maxOfMin(arr, n);
            
            for (int i = 0; i < n; i++) 
                System.out.print (res[i] + " ");
            System.out.println ();
        }
    }
}// } Driver Code Ends



class Solution 
{
    public static int[] maxOfMin(int[] arr, int n){
        int[] nse = nextSmallerElementIndices(arr, n);
        int[] pse = previousSmallerElement(arr, n);
    
        int[] answer = new int[n];
        Arrays.fill(answer, Integer.MIN_VALUE);
        
        for (int i = 0; i < n; i++){
            int width = nse[i] - pse[i] - 1;
            answer[width - 1] = Math.max(answer[width - 1], arr[i]);
        }
        for (int i = n - 2; i >= 0; i--)
            answer[i] = Math.max(answer[i], answer[i + 1]);
        return answer;
    }
    
    private static int[] nextSmallerElementIndices(int[] arr, int n){
        int[] NSE = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = n-1; i >= 0; i--){
            while (!stack.isEmpty()  &&  arr[i] <= arr[stack.peek()])
                stack.pop();

            NSE[i] = !stack.isEmpty() ? stack.peek() : n;
            stack.push(i);
        }
        return NSE;
    }

    private static int[] previousSmallerElement(int[] arr, int n){
        int[] PSE = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++){
            while (!stack.isEmpty()  &&  arr[stack.peek()] >= arr[i])
                stack.pop();

            PSE[i] = !stack.isEmpty() ? stack.peek() : -1;
            stack.push(i);
        }
        return PSE;
    }
}