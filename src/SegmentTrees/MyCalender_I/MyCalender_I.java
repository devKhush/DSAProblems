package SegmentTrees.MyCalender_I;

// Segment Tree is a basically a Binary tree used for storing the intervals or segments.
// Each node in the Segment Tree represents an interval

/******************************** Efficient Segment Tree Solution *************************************
 * Intuition:
    * We are using a BST here
    * This is follow up for "MyCalender_I" Binary Solution
    * In binary Search Solution, what costly operation we were doing?
    * After figuring out the index for insertion (by binary Search) we were adding booking at that index
        This addition of an element in at any index in ArrayList takes O(n) in worst case.
    * Due to this, the time complexity was O(log(n)) + O(n) in worst case

    * So, Here the improvement will be addition of input booking in Sorted Position in O(1) Time
    * We will use a BST/Segment Tree for this purpose.

 * 'n' --> number of bookings stored/done so far

 * Time Complexity: O(log(n))   for each call of book()
 * Space Complexity: O(n)
    * We will store the every booking in the form of TreeNode (if not found overlapping)
 */
public class MyCalender_I {
    private TreeNode root;
    public MyCalender_I() {
    }

    public boolean book(int start, int end) {
        // If there is no booking done yet, just add it
        if (root == null){
            root = new TreeNode(start, end);
            return true;
        }
        // Recursive Solution
        // return insertBooking(start, end, root);


        // Iterative Solution for Inserting a Booking
        // Same Logic as Below (Read Intuition Below for Inserting a Booking)
        // Time Complexity: O(log(n))
        // Space Complexity: O(1)
        TreeNode node = root;
        while (true){
            if (end <= node.start){
                if (node.left == null){
                    node.left = new TreeNode(start, end);
                    return true;
                }
                else
                    node = node.left;
            }
            else if (node.end <= start){
                if (node.right == null){
                    node.right = new TreeNode(start, end);
                    return true;
                }
                else
                    node = node.right;
            }
            else
                return false;
        }
    }

    // Recursive Solution for Inserting a Booking
    // Time Complexity: O(log(n))
    // Space Complexity: O(log(n))
    /** ******************* Intuition Behind the logic of the InsertBook() Function *******************

     * Timing Division w.r.t the Booking present at Root Node:
     * [~~~~~~~~~~~Left-Timings~~~~~~~~~~~] [~~Root-Node-Timings~~] [~~~~~~~~~~~Right-Timings~~~~~~~~~~~]

     * Timings of Booking to be inserted (without over-lapping) must either lie on the Left or Right
        side of Root node's Timings
     * If it is not lying on the either side of Root Booking's timings, then its timings is clashing
        with Root Node Booking's timing [Return false]
     */

    public boolean insertBooking(int start, int end, TreeNode root){
        // If the end time of booking to be inserted is lesser (<=) than start time of root
        // Then booking must be inserted on the left side of root. Eliminate Right half from search space
        // Recurse for the BST/Segment-Tree on the Left side of root
        if (end <= root.start){
            // Base case for insertion in left side of the Root
            if (root.left == null){
                root.left = new TreeNode(start, end);
                return true;
            }
            else
                return insertBooking(start, end, root.left);
        }
        // If the start time of booking to be inserted is greater (>=) than end time of root
        // Then booking must be inserted on the right side of root. Eliminate Left half from search space
        // Recurse for the BST/Segment-Tree on the Right side of root
        else if (root.end <= start){
            // Base case for insertion in right side of the Root
            if (root.right == null){
                root.right = new TreeNode(start, end);
                return true;
            }
            else
                return insertBooking(start, end, root.right);
        }
        // If none of the condition became True, then the timings of Booking was Over-lapping with
        // the timings of Root Node. So, the booking can't be inserted. Return False
        return false;
    }

    // ****************************************** Tree Node ******************************************
    private static class TreeNode{
        int start, end;
        TreeNode left, right;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
