package SinglyLinkedList.DetectLoop;

// https://leetcode.com/problems/linked-list-cycle-ii/

public class LinkedListCycleII {
    static class ListNode{
        int data;
        ListNode next;
        public ListNode(int data) {
            this.data = data;
        }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow==fast)
                break;
        }
        if (fast==null || fast.next==null)
            return null;
        slow = head;
        while (slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
