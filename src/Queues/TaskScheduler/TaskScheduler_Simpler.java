package Queues.TaskScheduler;
import java.util.Arrays;

// https://www.youtube.com/watch?v=eGf-26OTI-A&t=5s

public class TaskScheduler_Simpler {
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