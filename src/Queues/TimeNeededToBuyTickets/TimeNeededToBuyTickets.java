package Queues.TimeNeededToBuyTickets;

import java.util.LinkedList;
import java.util.Queue;

public class TimeNeededToBuyTickets {

    // Based on personal approach, THINK of this algo...
    public int timeRequiredToBuy(int[] tickets, int k) {
        int seconds = 0;
        int n = tickets.length;
        int i = 0;
        
        while (tickets[k] != 0){
            if (tickets[i] != 0) {
                tickets[i]--;
                seconds++;
            }
            i++;
            if (i == n)
                i = 0;
        }
        return seconds;
    }


    public int timeRequiredToBuy_UsingQueeu(int[] tickets, int k) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i<tickets.length; i++)
            queue.add(i);

        int seconds = 0;

        while(!queue.isEmpty()){
            int i = queue.remove();
            tickets[i]--;
            seconds++;

            if (tickets[i] == 0){
                if (i == k)     // when kth person has all the tickets purchased, so return answer
                    return seconds;
                else            // when some other person has all the tickets purchased, so removed
                    continue;
            }
            else                // when tickets are not finished, add backs to queue
                queue.add(i);
        }
        return -1;
    }
}


/*
My PYTHON Solution:

class Solution:
    def timeRequiredToBuy(self, tickets: List[int], k: int) -> int:
        queue = []
        for i, val in enumerate(tickets):
            queue.append([i, val])

        time = 0
        while queue:
            index, ticket = queue.pop(0)
            ticket -= 1
            time += 1

            if ticket == 0:
                if k == index:
                    return time
            else:
                queue.append([index, ticket])

        return None
 */