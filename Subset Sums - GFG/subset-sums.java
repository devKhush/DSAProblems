// { Driver Code Starts
//Initial Template for Java
import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0)
        {
            int N=sc.nextInt();
			ArrayList<Integer> arr = new ArrayList<>();
			for(int i = 0; i < N ; i++){
			    arr.add(sc.nextInt());
			}
            Solution ob = new Solution();
         
            ArrayList<Integer> ans = ob.subsetSums(arr,N);
            Collections.sort(ans);
            for(int sum : ans){
                System.out.print(sum+" ");
            }
            System.out.println();
        }  
    }
}
// } Driver Code Ends


//User function Template for Java//User function Template for Java
class Solution{
    public ArrayList<Integer> subsetSums(ArrayList<Integer> arr, int N){
        // code here
        ArrayList<Integer> answer = new ArrayList<>();
        
        getSubSequences(0, 0, arr, answer);
        
        return answer;
    }
    
    public void getSubSequences(int i, int sum, ArrayList<Integer> arr, ArrayList<Integer> answer){
        if (i == arr.size()){
            answer.add(sum);
            return;
        }
        
        getSubSequences(i+1, sum + arr.get(i), arr, answer);
        
        getSubSequences(i+1, sum, arr, answer);

    }
}