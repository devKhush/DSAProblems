package DoublyLinkedList.FlattenMultilevelDoublyLinkedList;

// https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/

public class FlattenMultilevelDoublyLinkedList {
    /*********************************** Solution 1 : Brute Force *******************************************
     * Intuition: Recursion + Brute Force
     * Approach:
        * Iterate through list and call recursion to flatten the list
        * Link the flatted child and again iterate

     * Time Complexity: O(c * n)
        * n is the total number of nodes in the list (including child nodes)
        * c is some constant
        * This is due to the Multiple passes (iterations over the) same child list.
     * Space Complexity: O(d)
        * d is the number of child branches (depth of child in each list)
     */
    public Node flatten___bruteforce(Node head) {
        Node p = head;

        // Traverse the list
        while (p != null) {
            if (p.child != null) {
                Node right = p.next;

                //Process child
                Node flattenChild = flatten(p.child);
                p.next = flattenChild;
                flattenChild.prev = p;
                p.child = null;

                while (p.next != null)
                    p = p.next;

                //Reconnect next
                if (right != null) {
                    p.next = right;
                    right.prev = p;
                }
            }
            p = p.next;
        }
        return head;
    }


    /*********************************** Most Efficient Solution *************************************
     * Intuition: Same as Before
        * Same Recursion
        * But this time we connect the first and last node in the child list. So, we needn't travel to
            the end of child list again.
        * By this modification, we remove the extra time wasted in repeated iterations on same child list.

     * Time Complexity: O(n)        Single pass Algorithm
     * Space Complexity: O(d)       Depth of child
     */
    public Node flatten(Node head) {
        if (head == null)
            return null;

        Node flatten = flatten_helper(head);
        head.prev.next = null;
        head.prev = null;
        return head;
    }

    public Node flatten_helper(Node head) {
        Node ptr = head;
        Node prev = null;

        while (ptr != null){
            if (ptr.child == null){
                prev = ptr;
                ptr = ptr.next;
                continue;
            }
            Node right = ptr.next;

            Node flattenChild = flatten_helper(ptr.child);
            Node lastNodeInFlattenChild = flattenChild.prev;

            ptr.next = flattenChild;
            flattenChild.prev = ptr;
            if (right != null){
                right.prev = lastNodeInFlattenChild;
                lastNodeInFlattenChild.next = right;
            }
            ptr.child = null;

            prev = lastNodeInFlattenChild;
            ptr = right;
        }
        head.prev = prev;
        prev.next = head;
        return head;
    }

    static class Node {
        public int val;
        public Node prev, next, child;
    }
}
