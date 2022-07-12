package Queues.TaskScheduler;
import java.util.Arrays;

// https://www.youtube.com/watch?v=eGf-26OTI-A&t=5s

public class TaskScheduler_Simpler {
    // Time Complexity :    O(n) + O(26 * log(26)) + O(25)  =  O(n)
    // Space Complexity:    O(Map Array of size: 26)  =  O(1)
    public int leastInterval(char[] tasks, int n) {
        int[] tasksCount = new int[26];

        for (char task : tasks)
            tasksCount[task - 'A']++;

        Arrays.sort(tasksCount);

        // This is the most frequent (greater in freq.) task to be performed
        int mostFrequentTask = tasksCount[25];

        // Idle slots that need to be filled are
        int idleSlots = (mostFrequentTask - 1) * n;

        // Filling Idle Slots
        for (int i = 24; i >= 0; i--)
            idleSlots -= Math.min(tasksCount[i], mostFrequentTask - 1);

        // Finding the Time taken to do all tasks
        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }
}

/*
Why min(char_map[i],max_val) ?

say it was 'aaabbb' the tasks and n = 2
now max is 3 tasks but last task not need to be given idle slots so, 2 tasks
2 * 2 = 4
now a__a__a is the situation where _ is idle slots
lets start filling
ab_ab_a
b fills 2 not three, the same reason
char_map['b'] will be 3, but we can only fill 2
the rest left b goes after last a
ab_ab_ab
so 2 idle slots 6 tasks hence 8 is result
 */