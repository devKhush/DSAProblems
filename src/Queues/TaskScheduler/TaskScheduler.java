package Queues.TaskScheduler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// https://www.youtube.com/watch?v=ySTQCRya6B0
// DRY RUN is a MUST

public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> count = new HashMap<>();
        
        for (char task : tasks)
            count.put(task, count.getOrDefault(task, 0) +1);
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b-a);
        maxHeap.addAll(count.values());

        int time = 0;
        
        while (!maxHeap.isEmpty()){
            Queue<Integer> queue = new LinkedList<>();

            // Why n+1 iterations? You need 1 cycle to do the most frequent task; then N cycles before
            // you can do it again. If there are other tasks you can do, fill up the N slots with them,
            // which is what removing from the heap. If maxHeap is empty in that loop, that means there
            // are none you can currently do, and you will be idle.
            for (int i=0; i<=n; i++){
                if (!maxHeap.isEmpty())
                    queue.add(maxHeap.remove());
            }
            
            int tasksDone = queue.size();
            
            while ( !queue.isEmpty() ){
                if (queue.peek() -1 != 0)
                    maxHeap.add(queue.remove() - 1);
                else
                    queue.remove();
            }
            
            time += maxHeap.isEmpty() ? tasksDone : n + 1;
        }
        
        return time;
    }
}