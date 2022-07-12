class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] tasksCount = new int[26];
        
        for (char task : tasks)
            tasksCount[task - 'A']++;
       
        Arrays.sort(tasksCount);
        
        int majorTask = tasksCount[25];
        int idleSlots = (majorTask - 1) * n;
        
        for (int i = 24; i >= 0; i--){
            idleSlots -= Math.min(tasksCount[i], majorTask - 1);    
        }
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }
}