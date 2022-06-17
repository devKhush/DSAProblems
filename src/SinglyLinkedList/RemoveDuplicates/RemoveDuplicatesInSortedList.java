package SinglyLinkedList.RemoveDuplicates;

// https://practice.geeksforgeeks.org/problems/remove-duplicate-element-from-sorted-linked-list/1


public class RemoveDuplicatesInSortedList {
    /*
    ********************************* Simple Logic **************************************
    * TC -> O(n)
    * SC -> O(1)
    */
    ListNode removeDuplicates(ListNode head) {
        if (head == null)
            return null;
        // Approach is using two pointers
        // 'curr' is current node & 'prev' is previous node
        ListNode curr = head, prev = null;

        while (curr != null){
            // If previous node was not null, then do this
            if (prev != null){

                // If value of 'prev' node is same as value of 'curr' node, then just delete the current node
                // using the reference of previous node & make 'curr' node as 'prev' node as 'prev' node was
                // unique node with unique value
                if (prev.val == curr.val){
                    prev.next = curr.next;
                    curr.next = null;
                    curr = prev;
                }

                // If value of 'prev' node is not same as value of 'curr' node, then we found a node with
                // unique value. So, just make 'prev' node as 'curr' & move ahead to find more duplicates node
                else
                    prev = curr;
            }

            // If previous node was null then just make 'prev' as curr. This case happens only when
            // 'curr' is head because only then 'prev' was 'null'
            else
                prev = curr;
            curr = curr.next;
        }

        return head;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
