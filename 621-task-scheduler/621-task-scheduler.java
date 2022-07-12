class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] taskCount = new int[26];
        
        for (char task : tasks)
            taskCount[task - 'A']++;
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        
        for (int task : taskCount)
            if (task != 0)
                maxHeap.add(task);
        
        int timeTaken = 0;
        
        while (!maxHeap.isEmpty()){
            Queue<Integer> queue = new ArrayDeque<>();
            
            for (int i = 0; i <= n; i++)
                if (!maxHeap.isEmpty())
                    queue.add(maxHeap.remove());
            
            int tasksDone = queue.size();
            
            while (!queue.isEmpty()){
                if (queue.peek() == 1)
                    queue.remove();
                else
                    maxHeap.add(queue.remove() - 1);
            }
            
            timeTaken += !maxHeap.isEmpty() ? n + 1 : tasksDone;
        }
        
        return timeTaken;
    }
    
    
    public int leastInterval_Compact_Efficient(char[] tasks, int n) {
        int[] tasksCount = new int[26];
        
        for (char task : tasks)
            tasksCount[task - 'A']++;
       
        Arrays.sort(tasksCount);
        
        int majorTask = tasksCount[25];
        int idleSlots = (majorTask - 1) * n;
        
        for (int i = 24; i >= 0; i--)
            idleSlots -= Math.min(tasksCount[i], majorTask - 1);    
        
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }
}