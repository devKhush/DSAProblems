package SinglyLinkedList.AddTwoNumbers;

// https://youtu.be/LBVsXSMOIk4
// https://takeuforward.org/data-structure/add-two-numbers-represented-as-linked-lists/


public class AddTwoNumbers {

    // **************************** Brute Force *************************************
    // Simple Logic of adding the values in linked-list while traversing

    // Complexity Analysis:
    // Time complexity : O(max(m,n)). Assume that m and n represents the length of l1 and l2 respectively,
    // the algorithm above iterates at most max(m,n) times.
    // Space complexity : O(max(m,n)). The length of the new list is at most max(m,n)+1.

    public ListNode addTwoNumbers_V1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = l1, ptr2 = l2, ptr = dummy;

        int carry = 0;

        while (ptr1 != null && ptr2 != null){
            int sum = ptr1.val + ptr2.val + carry;
            carry = sum/10;

            ptr.next = new ListNode(sum % 10);

            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
            ptr = ptr.next;
        }

        while (ptr1 != null){
            int sum = ptr1.val + carry;
            carry = sum/10;

            ptr.next = new ListNode(sum % 10);

            ptr = ptr.next;
            ptr1 = ptr1.next;
        }

        while (ptr2 != null){
            int sum = ptr2.val + carry;
            carry = sum/10;

            ptr.next = new ListNode(sum % 10);

            ptr = ptr.next;
            ptr2 = ptr2.next;
        }

        if (carry == 1)
            ptr.next = new ListNode(carry);

        return dummy.next;
    }



    // **************************** Short hand Code *************************************
    // Another Shorter Code that does same thing
    // Simple Logic of adding the values in linked-list while traversing
    // TC -> O(max(m,n))
    // SC -> O(max(m,n) + 1) = O(max(m,n))      +1 due to Node for storing carry

    public ListNode addTwoNumbers_V2(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode ptr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null){
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int sum = val1 + val2 + carry;
            ptr.next = new ListNode(sum % 10);
            carry = sum / 10;
            ptr = ptr.next;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        if (carry == 1)
            ptr.next = new ListNode(1);

        return dummy.next;
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }
}
