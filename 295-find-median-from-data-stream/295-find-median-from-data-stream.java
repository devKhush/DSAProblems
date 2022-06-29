class MedianFinder {
    private ArrayList<Integer> numbers;
    private int n;
    
    public MedianFinder() {
        this.numbers = new ArrayList<>();    
    }
    
    public void addNum(int num) {
        int low = 0, high = n - 1;
                
        while (low <= high){
            int mid = (low + high) >> 1;
            
            if (numbers.get(mid) <= num)
                low = mid + 1;
            else if (numbers.get(mid) > num)
                high = mid - 1;
        }
        
        numbers.add(low, num);
        n++;
    }
    
    public double findMedian() {                
        if (n % 2 == 1)
            return numbers.get(n / 2);
        else
            return (numbers.get(n/2) + numbers.get(n/2 - 1)) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */