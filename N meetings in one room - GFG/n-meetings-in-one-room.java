// { Driver Code Starts
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
         ArrayList<Meeting> allMeetings = new ArrayList<>();

        for (int i = 0; i < end.length; i++)
            allMeetings.add(new Meeting(start[i], end[i]));

        Collections.sort(allMeetings, new MeetingComparator());


        // 1st meeting with the smallest ending time is performed at start
        int meetingsPerformed = 0;
        int previousMeetingEndTime = -1;


        for (int i = 0; i < allMeetings.size(); i++){
            Meeting currMeeting = allMeetings.get(i);

            if (currMeeting.start > previousMeetingEndTime){
                previousMeetingEndTime = currMeeting.end;
                meetingsPerformed++;
            }
        }

        return meetingsPerformed;
    }
}

class Meeting{
    int start, end;
    public Meeting(int start, int end){
        this.start = start;
        this.end = end;
    }
}

class MeetingComparator implements Comparator<Meeting> {
    @Override
    public int compare(Meeting a, Meeting b){
        // Sort all the meetings by increasing order of their ending points
        return a.end - b.end;
    }
}
