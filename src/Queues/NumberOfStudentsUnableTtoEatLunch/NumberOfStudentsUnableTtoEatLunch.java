package Queues.NumberOfStudentsUnableTtoEatLunch;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfStudentsUnableTtoEatLunch {

    // O(n) using queue
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new LinkedList<>();

        // adds all students to the queue
        for (int i=0; i<students.length; i++)
            queue.add(students[i]);
        
        int i = 0;
        
        while (!queue.isEmpty() && queue.contains(sandwiches[i])){

            // sandwich of current student
            int currentStudentPreference = queue.remove();

            // students got his sandwich so removed
            if (currentStudentPreference == sandwiches[i])
                i++;
            // student didn't get his sandwich, so goes to the last
            else
                queue.add(currentStudentPreference);
        }

        // some students may be remaining for whom sandwich is no there in stack
        return queue.size();
    }
}