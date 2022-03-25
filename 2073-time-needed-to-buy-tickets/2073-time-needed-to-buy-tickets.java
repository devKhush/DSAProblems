class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int seconds = 0;
        int n = tickets.length;
        int i = 0;
        
        while (tickets[k] != 0){
            if (tickets[i] != 0){
                tickets[i]--;
                seconds++;
            }
            
            i++;
            if (i == n)
                i = 0;
        }
        
        return seconds;
    }
}