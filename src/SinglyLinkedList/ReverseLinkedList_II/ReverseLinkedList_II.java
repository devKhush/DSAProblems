package SinglyLinkedList.ReverseLinkedList_II;

// https://leetcode.com/problems/reverse-linked-list-ii/description/

public class ReverseLinkedList_II {
    /************************************* Double pass Traversal ************************************
     * Time Complexity: O(2*n)
     * Space Complexity: O(1)
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;

        ListNode dummy = new ListNode();
        dummy.next = head;
        int count = 0;
        ListNode start = null, end =  null, startBefore = null, endAfter = null;
        ListNode p = dummy;
        while (p != null){
            if (count == left - 1){
                startBefore = p;
                start = p.next;
            }
            if (count == right){
                end = p;
                endAfter = p.next;
            }
            p = p.next;
            count++;
        }
        startBefore.next = null;
        end.next = null;
        reverse(start);
        startBefore.next = end;
        start.next = endAfter;
        return dummy.next;
    }

    public void reverse(ListNode head){
        ListNode curr = head, prev = null, next;
        while (curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
    }


    static class ListNode{
        int data;
        ListNode next;
        public ListNode(){

        }
    }
}
