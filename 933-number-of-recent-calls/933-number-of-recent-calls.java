class RecentCounter {

    private Queue<Integer> queue;
    
    public RecentCounter() {
        this.queue = new LinkedList<>();
    }
    
    public int ping(int time) {
        int startRange = time -3000;
        
        this.queue.add(time);
        
        while (this.queue.peek() < startRange)
            this.queue.remove();
        
        return this.queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */