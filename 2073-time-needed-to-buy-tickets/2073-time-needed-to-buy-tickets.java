class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        int timeRequired = 0;
        
        for (int i = 0; i < tickets.length; i++)
            if (tickets[i] != 0)
                queue.add(i);
        
        while (tickets[k] != 0){
            int index = queue.remove();
            tickets[index]--;
            timeRequired++;

            if (tickets[index] != 0)
                queue.add(index);
        }
        return timeRequired;
    }
}