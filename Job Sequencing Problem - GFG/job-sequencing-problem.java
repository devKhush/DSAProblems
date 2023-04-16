//{ Driver Code Starts
import java.io.*;
import java.lang.*;
import java.util.*;

class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z; 
    }
}

class GfG {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //testcases
		int t = Integer.parseInt(br.readLine().trim());
		while(t-->0){
            String inputLine[] = br.readLine().trim().split(" ");
            
            //size of array
            int n = Integer.parseInt(inputLine[0]);
            Job[] arr = new Job[n];
            inputLine = br.readLine().trim().split(" ");
            
            //adding id, deadline, profit
            for(int i=0, k=0; i<n; i++){
                arr[i] = new Job(Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]));
            }
            
            Solution ob = new Solution();
            
            //function call
            int[] res = ob.JobScheduling(arr, n);
            System.out.println (res[0] + " " + res[1]);
        }
    }
}
// } Driver Code Ends


class Solution{
    int[] JobScheduling(Job arr[], int n){
        Arrays.sort(arr, (a,b)-> (a.profit != b.profit ? b.profit - a.profit : b.deadline - a.deadline));
        // Arrays.sort(arr, (a,b)-> (b.profit - a.profit));
        
        
        int[] deadlines = new int[n + 1];
        int jobs = 0;
        int profitsEarned = 0;
        
        for (Job job : arr){
            int deadline = job.deadline;
            while (deadline >= 1 && deadlines[deadline] != 0)
                deadline--;
            deadlines[deadline] = job.profit;
            
            if (deadline == 0) continue;
            jobs++;
            profitsEarned += job.profit;
        }
        return new int[]{jobs, profitsEarned};
    }
}

/*
class Job {
    int id, profit, deadline;
    Job(int x, int y, int z){
        this.id = x;
        this.deadline = y;
        this.profit = z; 
    }
}
*/