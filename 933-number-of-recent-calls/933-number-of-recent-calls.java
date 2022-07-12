class RecentCounter {
    private Queue<Integer> queue;
    
    public RecentCounter() {
        queue = new ArrayDeque<>();    
    }
    
    public int ping(int t) {
        int startRange = t - 3000;
        int endRange = t;
        
        while (!queue.isEmpty()  &&  queue.peek() < startRange)
            queue.remove();
        
        queue.add(t);
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */