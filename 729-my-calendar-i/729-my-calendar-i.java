class MyCalendar {    
    private TreeNode root;
    public MyCalendar() {
    }
    
    public boolean book(int start, int end) {
        if (root == null){
            root = new TreeNode(start, end);
            return true;
        }
        return insertBooking(start, end, root);
    }
    
    public boolean insertBooking(int start, int end, TreeNode root){
        if (end <= root.start){
            if (root.left == null){
                root.left = new TreeNode(start, end);
                return true;
            }
            else
                return insertBooking(start, end, root.left);
        }
        else if (root.end <= start){
            if (root.right == null){
                root.right = new TreeNode(start, end);
                return true;
            }
            else
                return insertBooking(start, end, root.right);
        }
        return false;
    }
    
    private static class TreeNode{
        int start, end;
        TreeNode left, right;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

// Brute Force ********************************************************************************
class MyCalendar_Brute {
    private ArrayList<int[]> bookings;
    
    public MyCalendar_Brute() {
        this.bookings = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (int[] booking : bookings){
            if (Math.max(start, booking[0]) < Math.min(end, booking[1]))
                return false;
        }
        
        bookings.add(new int[]{start, end});
        return true;
    }
}


// Binary Search ******************************************************************************
class MyCalendar_BinarySearch {
    private ArrayList<int[]> bookings;

    public MyCalendar_BinarySearch() {
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