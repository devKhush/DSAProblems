class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
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
    
    
    
    public int countStudents_Solution1(int[] students, int[] sandwiches) {
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