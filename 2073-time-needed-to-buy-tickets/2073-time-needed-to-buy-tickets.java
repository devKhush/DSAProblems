class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int timeRequired = 0;

        for (int i = 0; i < tickets.length; i++){
            if (i <= k)
                timeRequired += Math.min(tickets[k], tickets[i]);
            else if (i > k)
                timeRequired += Math.min(tickets[k] - 1, tickets[i]);
        }
        return timeRequired;
    }
    
    
    public int timeRequiredToBuy_BruteForce(int[] tickets, int k) {
        int n = tickets.length;
        int i = 0;
        int timeRequired = 0;

        while (tickets[k] != 0){
            if (tickets[i] != 0){
                tickets[i]--;
                timeRequired++;
            }
            i = (i + 1) % n;
        }
        return timeRequired;
    }


    public int timeRequiredToBuy_QueueSolution(int[] tickets, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        int timeRequired = 0;

        for (int i = 0; i < tickets.length; i++)
            if (tickets[i] != 0)
                queue.add(i);

        while (tickets[k] != 0){
            int index = queue.remove();
            tickets[index]--;
            timeRequired++;

            // When some other person has all the tickets purchased, so eliminate that person from Queue
            // Else if his tickets are not finished, add back him to the queue
            if (tickets[index] != 0)
                queue.add(index);
        }
        return timeRequired;
    }
}