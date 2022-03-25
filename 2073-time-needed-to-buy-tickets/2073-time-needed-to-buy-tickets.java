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
                if (i == k)
                    return seconds;
            }
            else
                queue.add(i);
        }
        
        return -1;
    }
}