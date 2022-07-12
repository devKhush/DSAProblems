package Queues.TimeNeededToBuyTickets;
import java.util.ArrayDeque;
import java.util.Queue;

public class TimeNeededToBuyTickets {
    /******************************* BRUTE FORCE *******************************************
     * Time complexity:     O(array[k])
        * Value of array at 'kth' position will decide time complexity, that many iterations of the
          whole array will be required to make array[k] equal to 0
        * Space Complexity: O(1)
     */
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


    /******************************* BRUTE FORCE: Queue Solution *******************************************
     * Time complexity:   O(n) +  O(array[k])
        * Value of array at 'kth' position will decide time complexity, that many iterations of the
          whole array will be required to make array[k] equal to 0
     * Space Complexity: O(n)   Queue of size 'n' is used
     */
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


    /************************************ Optimal Solution *******************************************
     * In previous solution, we were doing repetitions in the whole array.
     * We can just observe and think about the situations
     *
     * Intuition: Since people are in Queue, all the persons before 'kth person' need to buy tickets.
          * All the persons before the 'kth' one must buy  "min(tickets[i], tickets[k])" until
            kth person completes his tickets
          * All the persons after the 'kth' one must buy  "min(tickets[i], tickets[k] - 1)" until
            kth person completes his tickets. "-1" because after 'kth' index kth person will also buy one ticket

     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    // Observe the pattern
    // Just Think about the Intuition...
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

}
