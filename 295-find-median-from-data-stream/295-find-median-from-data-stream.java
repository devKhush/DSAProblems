class MedianFinder {
    private final PriorityQueue<Integer> leftNumbersMaxHeap;
    private final PriorityQueue<Integer> rightNumbersMinHeap;
    
    public MedianFinder() {
        leftNumbersMaxHeap = new PriorityQueue<>((a, b) -> (b - a));
        rightNumbersMinHeap = new PriorityQueue<>((a, b) -> (a - b));
    }
    
    public void addNum(int num) {
        if (leftNumbersMaxHeap.size() == 0)
            leftNumbersMaxHeap.add(num);
        
        else if (num <= leftNumbersMaxHeap.peek()){
            leftNumbersMaxHeap.add(num);
            
            if (leftNumbersMaxHeap.size() > rightNumbersMinHeap.size() + 1)
                rightNumbersMinHeap.add(leftNumbersMaxHeap.remove());
        }
        else{
            rightNumbersMinHeap.add(num);
            
            if (rightNumbersMinHeap.size() > leftNumbersMaxHeap.size())
                leftNumbersMaxHeap.add(rightNumbersMinHeap.remove());
        }
    }
    
    public double findMedian() {
        if (leftNumbersMaxHeap.size() == rightNumbersMinHeap.size())
           return (leftNumbersMaxHeap.peek() + rightNumbersMinHeap.peek()) / 2.0;
        
        if (leftNumbersMaxHeap.size() == rightNumbersMinHeap.size() + 1)
            return leftNumbersMaxHeap.peek();
        
        return -1;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */