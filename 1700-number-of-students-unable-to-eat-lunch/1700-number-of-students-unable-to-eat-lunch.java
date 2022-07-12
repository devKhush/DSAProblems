class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
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