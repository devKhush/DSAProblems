class RecentCounter {
    
    private Queue<Integer> queue;

    public RecentCounter() {
        queue = new LinkedList<>();
    }
    
    public int ping(int t) {
        int requests = 1;
        int startRange = t - 3000;
        int endRange = t;
                            
                            
        while (!queue.isEmpty()){
            if (queue.peek() >= startRange && queue.peek() <= endRange){
                requests += queue.size();
                break;
            }
            else
                queue.remove();
        }
        queue.add(t);
        
        return requests;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */