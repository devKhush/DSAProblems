package BinarySearch.MyCalendar_I;
import java.util.ArrayList;

/*************************************** Brute Force ********************************************
 * Idea is simple
 * We will add all the booking into a ArrayList
 * If the booking (to be added) is not overlapping with any of the bookings present in the
    bookings ArrayList, then add it.

 * Time Complexity: O(n)
 * Time Complexity: O(2 * n) = O(n)
 */

public class MyCalender_I_BruteForce {
    private final ArrayList<int[]> bookings;

    public MyCalender_I_BruteForce() {
        this.bookings = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] booking : bookings){
            // If the booking (to be added) is not overlapping with any of the bookings present in the
            // bookings ArrayList. See Below
            if (Math.max(start, booking[0]) < Math.min(end, booking[1]))
                return false;
        }

        // Add booking into the list
        bookings.add(new int[]{start, end});
        return true;
    }
}

/*
IMP:
******************************** IDEA to Detect Overlap in Two Intervals ********************************
* This is not just limited to this Question

How to calculate overlap of 2 intervals
Assume a and b are two booking intervals, where interval b is to be inserted.

There could be 4 case, but in any case, an overlap (either positive or negative) can always be
represented as:
    " max(a0, b0) < min(a1, b1) "

case 1: b ends before a ends:   (Overlap)   This can be reverse (positions of a & b can be switched)
a: a0 |-------------| a1
b:     b0 |-----| b1

case 2: b ends after a ends:    (Overlap)
a: a0 |--------| a1
b:     b0 |--------| b1

case 3: b starts after a ends:  (No Overlap)
a: a0 |----| a1
b:              b0 |----| b1

case 4: b starts before a:      (Overlap)
a:     a0 |--------| a1
b: b0 |--------| b1

***** If there was no overlap, " max(a0, b0) >= min(a1, b1) "  indicates no Overlap
***** If there was an overlap, " max(a0, b0) < min(a1, b1) "  indicates presence of Overlap
 */