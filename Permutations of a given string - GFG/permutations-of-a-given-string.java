// { Driver Code Starts
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
	public static void main(String[] args) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t-->0)
        {
            String S = br.readLine().trim();
            Solution obj = new Solution();
            List<String> ans = obj.find_permutation(S);
            for( int i = 0; i < ans.size(); i++)
            {
                System.out.print(ans.get(i)+" ");
            }
            System.out.println();
                        
        }
	}
}

// } Driver Code Ends


class Solution {
    List<String> list ;
    
    public List<String> find_permutation(String S) {
        // Code here
        list = new ArrayList<>();
        
        permutations(S.toCharArray(), new boolean[S.length()], "", 0);
        
        Collections.sort(list);
        
        return list;
    }
    
    private void permutations(char[] arr, boolean[] visited, String currentPermutation, int elementTaken){
        if (elementTaken==arr.length)
            list.add(currentPermutation);
        else{
            for (int i=0; i<arr.length; i++){
                if (!visited[i]){
                    visited[i] = true;
                    permutations(arr, visited, currentPermutation + arr[i], elementTaken+1);
                    visited[i] = false;
                }
                
            }   
        }
    }  
}