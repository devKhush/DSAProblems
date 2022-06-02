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
        ArrayList<Integer> subsetSums = new ArrayList<>();

        getSubSetSums(0, 0, arr, subsetSums);
        return subsetSums;
    }
    
    private void getSubSetSums(int index, int sum,  ArrayList<Integer> arr, ArrayList<Integer> answer) {
        answer.add(sum);

        for (int i = index; i < arr.size(); i++) {

            sum += arr.get(i);
            getSubSetSums(i + 1, sum, arr, answer);
            sum -= arr.get(i);
        }
    }
}