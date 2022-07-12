package Queues.NumberOfStudentsUnableTtoEatLunch;
import java.util.ArrayDeque;
import java.util.Queue;

public class NumberOfStudentsUnableTtoEatLunch {

    /* ************************************** SOLUTION: 1 ******************************************
     * Using Queue
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int countStudents_1(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new ArrayDeque<>();

        // adds all students to the queue
        for (int student : students)
            queue.add(student);
        
        int i = 0;
        
        while (!queue.isEmpty() && queue.contains(sandwiches[i])){
            // sandwich of current student
            int currentStudentPreference = queue.remove();

            // students got his sandwich, so student and sandwich removed
            if (currentStudentPreference == sandwiches[i])
                i++;
            // student didn't get his sandwich, so goes to the last
            else
                queue.add(currentStudentPreference);
        }
        // some students may be remaining for whom sandwich is no there in stack
        return queue.size();
    }



    /* ************************************** SOLUTION: 2 ******************************************
     * Using Queue
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int countStudents_2(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new ArrayDeque<>();

        // adds all students to the queue
        for (int student : students)
            queue.add(student);

        int i = 0;
        int sentToBackOfQueueCount = 0;

        while (!queue.isEmpty()  &&  sentToBackOfQueueCount != queue.size()){
            // sandwich of current student
            int currentStudentPreference = queue.remove();

            // students got his sandwich, so student and sandwich removed
            if (currentStudentPreference == sandwiches[i]){
                i++;
                sentToBackOfQueueCount = 0;
            }
            // student didn't get his sandwich, so goes to the last
            else{
                sentToBackOfQueueCount++;
                queue.add(currentStudentPreference);
            }
        }
        // some students may be remaining for whom sandwich is no there in stack
        return queue.size();
    }


    /* ************************************** SOLUTION: 3 ******************************************
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int countStudents_Optimal(int[] students, int[] sandwiches) {
        // Count the number of students who want "Type 0" & "Type 1" sandwiches
        int zeros = 0, ones = 0;
        for (int student : students){
            if (student == 1)
                ones++;
            else
                zeros++;
        }

        for (int sandwich : sandwiches){
            // If the current sandwich is "Type 0", and if there is any student who want "Type 0" sandwich
            // Then after few back rotations he will be able to take it for sure and that student will go away
            // Else, if there are no students who want "Type 0" sandwich. Then current sandwich can't be
            // picked up. Hence, remaining students who want "Type 1" sandwich will remain hungry
            if (sandwich == 0){
                if (zeros == 0)
                    return ones;
                else
                    zeros--;
            }
            // If the current sandwich is "Type 1", and if there is any student who want "Type 1" sandwich
            // Then after few back rotations he will be able to take it for sure and that student will go away
            // Else, if there are no students who want "Type 1" sandwich. Then current sandwich can't be
            // picked up. Hence, remaining students who want "Type 0" sandwich will remain hungry
            else if (sandwich == 1){
                if (ones == 0)
                    return zeros;
                else
                    ones--;
            }
        }
        return 0;
    }
}