// { Driver Code Starts
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
}// } Driver Code Ends


class Solution{
    //Function to find the maximum profit and the number of jobs done.
    int[] JobScheduling(Job[] allJobs, int n) {
       Arrays.sort(allJobs, new JobComparator());

        int maxDeadline = 0;
        for (Job jobs : allJobs)
            maxDeadline = Math.max(maxDeadline, jobs.deadline);

        int[] deadlinesDone = new int[maxDeadline + 1];
        int profit = 0, jobsDone = 0;
        
        for (int i = 0; i < allJobs.length; i++){
            int currentJobDeadline = allJobs[i].deadline;
            
          while (currentJobDeadline > 0  &&  deadlinesDone[currentJobDeadline] != 0)
                currentJobDeadline--;
            
            if (currentJobDeadline == 0)
                continue;
            
            deadlinesDone[currentJobDeadline] = allJobs[i].id;
            profit += allJobs[i].profit;
            jobsDone++;
        }
        return new int[]{jobsDone, profit};
    }
    
    class JobComparator implements Comparator<Job>{
        @Override
        public int compare(Job a, Job b){
            // Sorting all the job wrt to profits, jobs with more profit are placed first
            return b.profit - a.profit;
        }
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