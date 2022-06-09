// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;


class pair  {  
    long first, second;  
    public pair(long first, long second)  
    {  
        this.first = first;  
        this.second = second;  
    }  
}

class GFG {
	public static void main(String[] args) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t-->0)
        {
            StringTokenizer stt = new StringTokenizer(br.readLine());
            
            long N = Long.parseLong(stt.nextToken());
            long M = Long.parseLong(stt.nextToken());
            long X = Long.parseLong(stt.nextToken());
            long A[] = new long[(int)(N)];
            long B[] = new long[(int)(M)];
            
            
            String inputLine[] = br.readLine().trim().split(" ");
            for (int i = 0; i < N; i++) {
                A[i] = Long.parseLong(inputLine[i]);
            }
            String inputLine1[] = br.readLine().trim().split(" ");
            for (int i = 0; i < M; i++) {
                B[i] = Long.parseLong(inputLine1[i]);
            }
            
            
            Solution obj = new Solution();
            pair [] answer = obj.allPairs(A, B, N, M, X);
            int sz = answer.length;
            
            if(sz==0)
            System.out.println(-1);
            else{
                StringBuilder output = new StringBuilder();
                for(int i=0;i<sz;i++){
                    if(i<sz-1)
                    output.append(answer[i].first +" "+ answer[i].second + ", ");
                    else
                    output.append(answer[i].first +" "+ answer[i].second);
                    
                }
                System.out.println(output);
            }
            
        }
	}
}
// } Driver Code Ends


//User function Template for Java

/*
class pair  {  
    long first, second;  
    public pair(long first, long second)  
    {  
        this.first = first;  
        this.second = second;  
    }  
}
*/

class Solution {
    public pair[] allPairs( long arr1[], long arr2[], long n, long m, long sum) {

        // HashSet<Long> set = new HashSet<>();
        // ArrayList<pair> list = new ArrayList<>();
        
        // for (long val1 : arr1)
        //     set.add(val1);
            
        // for (long val2 : arr2)
        //     if (set.contains(sum - val2))
        //         list.add(new pair(sum - val2, val2));
                
        // pair[] pairs = new pair[list.size()];
        // for (int i = 0; i < list.size(); i++)
        //     pairs[i] = list.get(i);
            
        // Arrays.sort(pairs, new PairComparator());
        // return pairs;
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        ArrayList<pair> list = new ArrayList<>();
        
        for (long val1 : arr1)
            for (long val2 : arr2)
                if (val1 + val2 == sum)
                    list.add(new pair(val1, val2));
        
        pair[] pairs = new pair[list.size()];
        for (int i = 0; i < list.size(); i++)
            pairs[i] = list.get(i);
            
        return pairs;
    }
}

class PairComparator implements Comparator<pair>{
    @Override
    public int compare(pair a, pair b){
        if (a.first != b.first)
            return a.first > b.first ? 1 : -1;
        else
            return (int)(a.second - b.second);
    }
}




