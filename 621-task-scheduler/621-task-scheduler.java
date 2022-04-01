class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] counts = new int[26];
        
        for (char task : tasks)
            counts[task - 'A']++;
        
        Arrays.sort(counts);
        
        int maxValue = counts[25] - 1;
        int idleSlots = maxValue * n;
        
        for (int i = 24; i>=0; i--)
            idleSlots -= Math.min(counts[i], maxValue);
        
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length; 
        
    }
}