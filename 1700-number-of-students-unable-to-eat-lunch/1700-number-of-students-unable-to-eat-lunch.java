class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i=0; i<students.length; i++)
            queue.add(students[i]);
        
        int i = 0;
        
        while (!queue.isEmpty() && queue.contains(sandwiches[i])){
            int currentStudentPreference = queue.remove();
            
            if (currentStudentPreference == sandwiches[i])
                i++;
            else
                queue.add(currentStudentPreference);
        }
        return queue.size();
    }
}