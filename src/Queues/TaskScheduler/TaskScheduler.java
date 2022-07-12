package Queues.TaskScheduler;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

// https://www.youtube.com/watch?v=ySTQCRya6B0
// DRY RUN is a MUST

public class TaskScheduler {
    // Time Complexity :   O(n) + O(26 * log(26) + O(n * 26 * log(26))  ~=  O(n)  (though not exactly O(n))
    // Space Complexity:   O(26) + O(26) + O(n)  =  O(n)
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

            // Why n+1 iterations? You need 1 cycle to do the most frequent task; then N cycles before
            // you can do it again. If there are other tasks you can do, fill up the N slots with them,
            // which is what removing from the heap. If maxHeap is empty in that loop, that means there
            // are none you can currently do, and you will be idle.
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

            // tasks done will be "n + 1" if we have more tasks to do. Else in the last remaining tasks/pass
            // tasks done will be queue's size
            timeTaken += !maxHeap.isEmpty() ? n + 1 : tasksDone;
        }

        return timeTaken;
    }
}