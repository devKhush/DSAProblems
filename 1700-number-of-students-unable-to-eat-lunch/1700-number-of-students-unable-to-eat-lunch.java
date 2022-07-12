class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        int zeros = 0, ones = 0;
        for (int student : students){
            if (student == 1)
                ones++;
            else
                zeros++;
        }

        for (int sandwich : sandwiches){
            if (sandwich == 0){
                if (zeros == 0)
                    return ones;
                else
                    zeros--;
            }
            else if (sandwich == 1){
                if (ones == 0)
                    return zeros;
                else
                    ones--;
            }
        }
        return 0;
    }
    
    public int countStudents_Queue_2(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new LinkedList<>();

        for (int student : students)
            queue.add(student);

        int i = 0;
        int sentToBackOfQueueCount = 0;

        while (!queue.isEmpty()  &&  sentToBackOfQueueCount != queue.size()){
            int currentStudentPreference = queue.remove();

            if (currentStudentPreference == sandwiches[i]){
                i++;
                sentToBackOfQueueCount = 0;
            }
            else{
                sentToBackOfQueueCount++;
                queue.add(currentStudentPreference);
            }
        }
        return queue.size();
    }
    
    
    
    public int countStudents_Queue_1(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new ArrayDeque<>();
        
        for (int student : students)
            queue.add(student);
        
        int i = 0;
        
        while (!queue.isEmpty()  &&  queue.contains(sandwiches[i])){
            int currentStudent = queue.remove();
            
            if (sandwiches[i] == currentStudent)
                i++;
            else
                queue.add(currentStudent);
        }
        return queue.size();
    }
}