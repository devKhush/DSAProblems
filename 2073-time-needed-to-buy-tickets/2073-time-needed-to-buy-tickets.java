class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
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