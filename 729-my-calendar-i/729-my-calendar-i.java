class MyCalendar {
    private ArrayList<int[]> bookings;

    public MyCalendar() {
        this.bookings = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        if (bookings.size() == 0){
            bookings.add(new int[]{start, end});
            return true;
        }
        
        int low = 0, high = bookings.size() - 1;
        
        while (low <= high){
            int mid = (low + high) >> 1;
            
            if (bookings.get(mid)[0] <= start)
                low = mid + 1;
            else if (bookings.get(mid)[0] > start)
                high = mid - 1;
        }
    
        int insertPosition = low;
        
        if (insertPosition == 0)
            return insertAtFront(start, end);
        
        if (insertPosition == bookings.size())
            return insertAtEnd(start, end);
        
        return insert(start, end, insertPosition);
    }
    
    private boolean insert(int start, int end, int insertPosition){
        int[] previousBooking = bookings.get(insertPosition - 1);
        int[] nextBooking = bookings.get(insertPosition);
        
        if (start < previousBooking[1])
            return false;
        if (end > nextBooking[0])
            return false;
        
        bookings.add(insertPosition, new int[]{start, end});
        return true;
    }
    
    private boolean insertAtFront(int start, int end){
        int[] nextBooking = bookings.get(0);    
        
        if (end > nextBooking[0])
            return false;
        
        bookings.add(0, new int[]{start, end});
        return true;
    }
    
    private boolean insertAtEnd(int start, int end){
        int[] previousBooking = bookings.get(bookings.size() - 1);
        
        if (start < previousBooking[1])
            return false;
        
        bookings.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */