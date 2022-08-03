package BinarySearch.MyCalendar_I;
import java.util.ArrayList;

/********************************** Simple Binary Search Solution ********************************
 * Pre-requisite: "Search Insert Position in Sorted array" Binary Search
 *
 * Intuition: Idea is to Insert all the Booking (in the form of [start,end]) in the "Sorted fashion"
    of "Start time" in an ArrayList
 * Before inserting a booking into the "allBooking's ArrayList", we need to do a quick check whether the
    booking to insert is overlapping or not.
 * If the booking to insert is not overlapping, just insert it & return true
 * If the booking to insert is overlapping, we can't insert it & return false

 * 'n' -> current size of Booking list
 * Time Complexity: O(log(n)) + O(n)
    * O(log(n)) to find the insert position for the current booking using Binary Search
    * O(n) because of adding the current Booking into the All-Bookings list at the insert position
        given by binary search
 * Space Complexity: O(2 * n) = O(n)
    We are storing pairs of (start, end) inside the All-Bookings list of size 'n'
 */

class MyCalendar_I {
    // ArrayList to store all the bookings
    private final ArrayList<int[]> bookings;

    public MyCalendar_I() {
        this.bookings = new ArrayList<>();
    }

    // Insert a Booking
    // Insert all the Booking (in the form of [start,end]) in the "Sorted fashion" according to
    // "Start time" in an ArrayList
    public boolean book(int start, int end) {
        if (bookings.size() == 0){          // If no booking has been added yet, just insert it
            bookings.add(new int[]{start, end});
            return true;
        }

        // Do a Binary Search to find out: Where to insert the current booking according to its Start time
        int low = 0, high = bookings.size() - 1;

        while (low <= high){
            int mid = (low + high) >> 1;
            
            if (bookings.get(mid)[0] <= start)
                low = mid + 1;
            else if (bookings.get(mid)[0] > start)
                high = mid - 1;
        }

        // The booking will be inserted into the sorted list (according to its Starting time) at the
        // index "low"
        int insertPosition = low;

        // If the position to insert the booking is 0, then check whether insertion is possible
        if (insertPosition == 0)
            return insertAtFront(start, end);

        // If the position to insert the booking is after last index (n), then check whether
        // insertion is possible.
        if (insertPosition == bookings.size())
            return insertAtEnd(start, end);

        // Else the insert position (index) is somewhere between the middle of ArrayList
        // Check whether insertion is possible
        return insert(start, end, insertPosition);
    }


    // Insert the booking at any index, 'i' (in between the list) only if:
    // 1) "its start time is greater or equal to the current booking at (i-1)th index"
    // 2) "Its end time is smaller or equal to the current booking at ith index"
    // Time Complexity: O(n)        Addition happens according to insert position
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

    // Insert the booking at index "0" (first position) only if "its end time is smaller or equal to the
    // current booking at 0th index" (i.e, It should end before the start of Booking at 0th index)
    // Time Complexity: O(1)
    private boolean insertAtFront(int start, int end){
        int[] nextBooking = bookings.get(0);    
        
        if (end > nextBooking[0])
            return false;

        bookings.add(0, new int[]{start, end});
        return true;
    }

    // Insert the booking at index "n" (last position) only if "its start time is greater or equal to the
    // current booking at (n-1)th index" (i.e, It should start after the end of Booking at (n-1)th index)
    // Time Complexity: O(1)
    private boolean insertAtEnd(int start, int end){
        int[] previousBooking = bookings.get(bookings.size() - 1);
        
        if (start < previousBooking[1])
            return false;
        
        bookings.add(new int[]{start, end});
        return true;
    }
}