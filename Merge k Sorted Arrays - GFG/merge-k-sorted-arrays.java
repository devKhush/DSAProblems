// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.io.*;

class GFG{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t > 0){
			int n = sc.nextInt();
			int[][] a = new int[n][n];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					a[i][j] = sc.nextInt();
			Solution T = new Solution();
			ArrayList<Integer> arr= T.mergeKArrays(a, n);
			for(int i = 0; i < n*n ; i++)
			    System.out.print(arr.get(i)+" ");
		    System.out.println();
		    
		    t--;
		}
	}
}

// } Driver Code Ends


//User function Template for Java


class Solution{
    //Function to merge k sorted arrays.
    public static ArrayList<Integer> mergeKArrays(int[][] kArrays,int k){
       ArrayList<Integer> array = new ArrayList<Integer>();
       
       PriorityQueue<Value> minHeap = new PriorityQueue<>();
       
       for (int i = 0; i < k; i++){
           Value minValue = new Value(kArrays[i][0], i, 0);
           minHeap.add(minValue);
       }
       
       while (!minHeap.isEmpty()){
           Value min = minHeap.remove();
           array.add(min.value);
           
           if (min.j < kArrays[min.i].length - 1)
            minHeap.add(new Value(kArrays[min.i][min.j + 1], min.i, min.j + 1));
       }
       return array;
    }
    
    public static class Value implements Comparable<Value>{
        int value, i, j;
        public Value(int value, int i, int j){
            this.value = value;
            this.i = i;
            this.j = j;
        }
        
        @Override
        public int compareTo(Value obj){
            return this.value - obj.value;
        }
    } 

    
}