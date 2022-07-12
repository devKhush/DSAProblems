class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int n = tickets.length;
        int i = 0;
        int time = 0;
        
        while (tickets[k] != 0){
            if (tickets[i] != 0){
                tickets[i]--;
                time++;
            }
            i = (i + 1) % n;
        }
        return time;
    }
}