//{ Driver Code Starts
import java.io.*;
import java.util.*;
import java.lang.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());

        while (t-- > 0) {
            String inputLine[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine[0]);

            int start[] = new int[n];
            int end[] = new int[n];

            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++)
                start[i] = Integer.parseInt(inputLine[i]);

            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) 
                end[i] = Integer.parseInt(inputLine[i]);
                
            int ans = new Solution().maxMeetings(start, end, n);
            System.out.println(ans);
        }
    }
}

// } Driver Code Ends


class Solution {
    public static int maxMeetings(int start[], int end[], int n){
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < start.length; i++){
            list.add(new int[]{start[i], end[i]});
        }
        list.sort((a,b)->(a[1]-b[1]));
        
        int meetings = 0;
        int prevEndTime = -1;
        for (int i = 0; i < list.size(); i++){
            int startTime = list.get(i)[0];
            if (startTime > prevEndTime){
                meetings++;
                prevEndTime = list.get(i)[1];
            }
        }
        return meetings;
    }
}
