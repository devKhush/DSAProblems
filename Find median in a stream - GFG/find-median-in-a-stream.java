// { Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;


class GFG
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        int n;
        while(t-- > 0){
            n = sc.nextInt();
    
            Solution obj = new Solution();
            for(int i = 1; i <= n; i++)
            {
                int x =sc.nextInt();
                obj.insertHeap(x);
                System.out.println((int)Math.floor(obj.getMedian()));
            }
        }
        
        
    }
}

// } Driver Code Ends


class Solution{
    private static final PriorityQueue<Integer> leftNumbersMaxHeap = new PriorityQueue<>((a, b) -> (b - a));
    private static final PriorityQueue<Integer> rightNumbersMinHeap = new PriorityQueue<>((a, b) -> (a - b));

    public static void insertHeap(int num){
        if (leftNumbersMaxHeap.size() == 0)
            leftNumbersMaxHeap.add(num);

        else if (num <= leftNumbersMaxHeap.peek()){
            leftNumbersMaxHeap.add(num);

            if (leftNumbersMaxHeap.size() > rightNumbersMinHeap.size() + 1)
                rightNumbersMinHeap.add(leftNumbersMaxHeap.remove());
        }
        else{
            rightNumbersMinHeap.add(num);

            if (rightNumbersMinHeap.size() > leftNumbersMaxHeap.size())
                leftNumbersMaxHeap.add(rightNumbersMinHeap.remove());
        }
    }
    
    //Function to balance heaps.
    public static void balanceHeaps(){
       // add your code here
    }
    
    //Function to return Median.
    public static double getMedian()    {
        if (leftNumbersMaxHeap.size() == rightNumbersMinHeap.size())
            return (leftNumbersMaxHeap.peek() + rightNumbersMinHeap.peek()) / 2.0;

        if (leftNumbersMaxHeap.size() == rightNumbersMinHeap.size() + 1)
            return leftNumbersMaxHeap.peek();

        return -1;
    }
    
}