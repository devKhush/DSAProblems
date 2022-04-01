class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> count = new HashMap<>();
        
        for (char task : tasks)
            count.put(task, count.getOrDefault(task, 0) +1);
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
        maxHeap.addAll(count.values());
        
        
        int time = 0;
        
        while (!maxHeap.isEmpty()){
            Queue<Integer> queue = new LinkedList<>();
            
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